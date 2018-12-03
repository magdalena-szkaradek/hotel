package app.service;

import app.entity.*;
import app.repository.ReservationIdRepository;
import app.repository.ReservationRepository;
import app.repository.RoomRepository;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    ReservationIdRepository reservationIdRepository;

    public Iterable<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation addNewReservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        User user = userRepository.finByUserId(reservationDTO.getUserId());
        user.setAmount_of_reservations(user.getAmount_of_reservations() + 1);
        reservation.setUser(user);
        reservation.setEndDate(reservationDTO.getEndDate());
        reservation.setStartDate(reservationDTO.getStartDate());
        reservation.setPayed(false);
        reservation.setTotalPrice(reservationDTO.getAverageCosts().stream().reduce(0., Double::sum));


        List<Room> collect = reservationDTO.getRooms().stream()
                .map(roomRepository::findById)
                .map(Optional::get)
                .collect(Collectors.toList());

        Double totalPrice = collect.stream().mapToDouble(Room::getPrice).sum();

        List<ReservationId> reservationIdList = collect.stream()
                .map(room -> {
                    ReservationId reservationId = new ReservationId();
                    reservationId.setRoom(room);
                    return reservationId;
                }).collect(Collectors.toList());

        reservation.setTotalPrice(totalPrice);
        reservation.setReservationIdList(reservationIdList);
        reservationIdRepository.saveAll(reservationIdList);

        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Integer reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    public List<Reservation> getReservationForUser(Integer userId) {
        return reservationRepository.findAllByUserId(userId);
    }
}
