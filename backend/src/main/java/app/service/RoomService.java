package app.service;

import app.entity.*;
import app.repository.ReservationRepository;
import app.repository.RoomRepository;
import app.repository.SeasonPriceRepository;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class RoomService {
    public static final Integer DISCOUNT_FOR_LOYAL = 10;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    SeasonPriceRepository seasonPriceRepository;

    public Iterable<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room addNewRoom(Room room) {
        return roomRepository.save(room);
    }

    public void deleteRoom(Integer id) {
        roomRepository.deleteById(id);
    }

    public SearchResultsDTO searchByCriteria(SearchCriteriaDTO searchCriteriaDTO) {
        List<Room> availableRooms = new LinkedList<>();
        List<RoomDTO> roomDTOList = new ArrayList<>();
        List<Room> roomList = new ArrayList<>();
        if (searchCriteriaDTO.getGuests() > 4) {
            roomRepository.findAll().forEach(roomList::add);
        } else {
            roomList = getRoomsWithAccurateNumberOfBeds(searchCriteriaDTO);
        }

        getAvailableRooms(searchCriteriaDTO, availableRooms, roomList);

        List<SeasonPrice> seasonPriceList = getSeasonPrices(searchCriteriaDTO);

        int daysOfReservation = getDaysOfReservation(searchCriteriaDTO);

        if (searchCriteriaDTO.getGuests() >= 15) {
            double priceForGroup = 10;
            for (Room room : availableRooms) {
                double pricePerRoom = priceForGroup * room.getBeds();
                RoomDTO roomDTO = prepareRoomDTO(room, pricePerRoom, pricePerRoom, daysOfReservation, 0, 0);
                roomDTOList.add(roomDTO);
            }
        } else {
            if (seasonPriceList.isEmpty()) {
                for (Room room : availableRooms) {

                    double averagePriceWithoutSeasoningSystem = calculateCostWithoutSeasoningSystem(daysOfReservation, room, searchCriteriaDTO.getUserId());
                    int normalDays = daysOfReservation;

                    RoomDTO roomDTO = prepareRoomDTO(room, averagePriceWithoutSeasoningSystem, averagePriceWithoutSeasoningSystem, normalDays, 0, 0);
                    roomDTOList.add(roomDTO);
                }
            }

            for (SeasonPrice seasonPrice : seasonPriceList) {
                Integer percentage = seasonPrice.getPercentage();
                int daysWithSeasonPrice = getDaysWithSeasonPrice(searchCriteriaDTO, daysOfReservation, seasonPrice);

                int normalDays = daysOfReservation - daysWithSeasonPrice;

                for (Room room : availableRooms) {
                    double averagePriceWithSeasoningSystem = calculateCostWithSeasoningSystem(daysOfReservation, percentage, daysWithSeasonPrice, normalDays, room, searchCriteriaDTO.getUserId());
                    double averagePriceWithoutSeasoningSystem = calculateCostWithoutSeasoningSystem(daysOfReservation, room, searchCriteriaDTO.getUserId());

                    RoomDTO roomDTO = prepareRoomDTO(room, averagePriceWithoutSeasoningSystem, averagePriceWithSeasoningSystem, normalDays, daysWithSeasonPrice, percentage);

                    roomDTOList.add(roomDTO);
                }
            }
        }


        SearchResultsDTO searchResultsDTO = new SearchResultsDTO();

        searchResultsDTO.setEndDate(searchCriteriaDTO.getEndDate());
        searchResultsDTO.setGuests(searchCriteriaDTO.getGuests());
        searchResultsDTO.setStartDate(searchCriteriaDTO.getStartDate());
        searchResultsDTO.setRoomList(roomDTOList);
        searchResultsDTO.setUserId(searchCriteriaDTO.getUserId());

        return searchResultsDTO;
    }

    private RoomDTO prepareRoomDTO(Room room, double averagePriceWithoutSeasoningSystem, double averagePriceWithSeasoningSystem,
                                   int normalDays, int extraPaidDays, int seasoningPercentage) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setBeds(room.getBeds());
        roomDTO.setId(room.getId());
        roomDTO.setName(room.getName());
        roomDTO.setPricePerNightWithSeasoningSystem(averagePriceWithSeasoningSystem);
        roomDTO.setPricePerNightWithoutSeasoningSystem(averagePriceWithoutSeasoningSystem);
        roomDTO.setExtraPaidDays(extraPaidDays);
        roomDTO.setNormalPaidDays(normalDays);
        roomDTO.setSeasoningPercentage(seasoningPercentage);
        return roomDTO;
    }

    private int getDaysOfReservation(SearchCriteriaDTO searchCriteriaDTO) {
        return (int) ChronoUnit.DAYS.between(searchCriteriaDTO.getStartDate(), searchCriteriaDTO.getEndDate());
    }

    private List<SeasonPrice> getSeasonPrices(SearchCriteriaDTO searchCriteriaDTO) {
        return seasonPriceRepository.findApplicableSeasoningPrices(searchCriteriaDTO.getStartDate(), searchCriteriaDTO.getEndDate());
    }

    private void getAvailableRooms(SearchCriteriaDTO searchCriteriaDTO, List<Room> availableRooms, List<Room> roomList) {
        List<Integer> reservationList = reservationRepository.getRoomsNotAvailable(searchCriteriaDTO.getStartDate(), searchCriteriaDTO.getEndDate())
                .stream()
                .flatMap(res -> res.getReservationIdList().stream())
                .map(resId -> resId.getRoom().getId())
                .collect(Collectors.toList());

        for (Room room : roomList) {
            if (!reservationList.contains(room.getId())) {
                availableRooms.add(room);
            }
        }
    }

    private List<Room> getRoomsWithAccurateNumberOfBeds(SearchCriteriaDTO searchCriteriaDTO) {
        return roomRepository.findRoomsWithAccurateNumberOfBeds(searchCriteriaDTO.getGuests());
    }

    private int getDaysWithSeasonPrice(SearchCriteriaDTO searchCriteriaDTO, int daysOfReservation, SeasonPrice seasonPrice) {
        int daysWithSeasonPrice = 0;

        if (seasonPrice.getStartDate().isAfter(searchCriteriaDTO.getStartDate())) {
            if (seasonPrice.getEndDate().isAfter(searchCriteriaDTO.getEndDate())) {
                daysWithSeasonPrice = (int) ChronoUnit.DAYS.between(seasonPrice.getStartDate(), searchCriteriaDTO.getEndDate());
            } else if (seasonPrice.getEndDate().isBefore(searchCriteriaDTO.getEndDate())) {
                daysWithSeasonPrice = (int) ChronoUnit.DAYS.between(seasonPrice.getStartDate(), seasonPrice.getEndDate());
            }
        }

        if (seasonPrice.getStartDate().isBefore(searchCriteriaDTO.getStartDate())) {
            if (seasonPrice.getEndDate().isAfter(searchCriteriaDTO.getEndDate())) {
                daysWithSeasonPrice = daysOfReservation;
            } else if (seasonPrice.getEndDate().isBefore(searchCriteriaDTO.getEndDate())) {
                daysWithSeasonPrice = (int) ChronoUnit.DAYS.between(searchCriteriaDTO.getStartDate(), seasonPrice.getEndDate());
            }
        }

        if (seasonPrice.getStartDate().isEqual(searchCriteriaDTO.getStartDate())) {
            if (searchCriteriaDTO.getEndDate().isBefore(seasonPrice.getEndDate())) {
                daysWithSeasonPrice = (int) ChronoUnit.DAYS.between(seasonPrice.getStartDate(), searchCriteriaDTO.getEndDate());
            }

            if (searchCriteriaDTO.getEndDate().isEqual(seasonPrice.getEndDate())) {
                daysWithSeasonPrice = daysOfReservation;
            }
            if (searchCriteriaDTO.getEndDate().isAfter(seasonPrice.getEndDate())) {
                daysWithSeasonPrice = (int) ChronoUnit.DAYS.between(seasonPrice.getStartDate(), seasonPrice.getEndDate());
            }
        }
        return daysWithSeasonPrice;
    }

    private double calculateCostWithoutSeasoningSystem(int daysOfReservation, Room room, Integer userId) {
        Double pricePerNight = room.getPrice();
        double totalCost = isLoyal(userId) ? applyLoyalDiscount(pricePerNight * daysOfReservation) : (pricePerNight * daysOfReservation);
        return totalCost / daysOfReservation;
    }

    private double calculateCostWithSeasoningSystem(int daysOfReservation, Integer percentage, int daysWithSeasonPrice, int normalDays, Room room, Integer userId) {
        double totalCostWithSeasonPerNight = 0;
        double normalTimeCost = normalDays * room.getPrice();
        double specialTimeCost = daysWithSeasonPrice * (room.getPrice() * percentage / 100);
        double totalCost = isLoyal(userId) ? applyLoyalDiscount(normalTimeCost + specialTimeCost) : (normalTimeCost + specialTimeCost);
        totalCostWithSeasonPerNight = totalCost / daysOfReservation;
        return totalCostWithSeasonPerNight;
    }


    private double applyLoyalDiscount(double reservationCost) {
        return (reservationCost * DISCOUNT_FOR_LOYAL) / 100;
    }

    private boolean isLoyal(Integer userId) {
        return userRepository.getAmountOfReservation(userId) > 3;
    }

    public Optional<Room> findRoomById(Integer id) {
        return roomRepository.findById(id);
    }
}
