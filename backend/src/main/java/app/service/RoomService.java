package app.service;

import app.entity.Room;
import app.entity.SearchCriteriaDTO;
import app.entity.SearchResultsDTO;
import app.repository.ReservationRepository;
import app.repository.RoomRepository;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReservationRepository reservationRepository;

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

        List<Room> roomList = roomRepository.findRoomsWithAccurateNumberOfBeds(searchCriteriaDTO.getGuests());

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
        //ToDo Sprawdz sezonowosc

        boolean isLoyal = userRepository.getAmountOfReservation(searchCriteriaDTO.getUserId()) > 3;
        //ToDo oblicz cene


        SearchResultsDTO searchResultsDTO = new SearchResultsDTO();
        searchResultsDTO.setEndDate(searchCriteriaDTO.getEndDate());
        searchResultsDTO.setGuests(searchCriteriaDTO.getGuests());
        searchResultsDTO.setStartDate(searchCriteriaDTO.getStartDate());
        searchResultsDTO.setRoomList(availableRooms);
        searchResultsDTO.setUserId(searchCriteriaDTO.getUserId());

        return searchResultsDTO;
    }
}
