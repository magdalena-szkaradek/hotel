package app.service;

import app.entity.*;
import app.repository.ReservationIdRepository;
import app.repository.ReservationRepository;
import app.repository.RoomRepository;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


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

    public Iterable<Reservation> getAllReservations(){
        return reservationRepository.findAll();
    }

    public Reservation addNewReservation(ReservationDTO reservationDTO){
        Reservation reservation = new Reservation();
        User user = userRepository.finByUserId(reservationDTO.getUserId());

        reservation.setUser(user);
        reservation.setEndDate(reservationDTO.getEndDate());
        reservation.setStartDate(reservationDTO.getStartDate());
        reservation.setPayed(reservationDTO.isPayed());

        List<ReservationId> reservationIdList = new ArrayList<>();

        for(Integer roomNumber : reservationDTO.getRooms()){
            ReservationId reservationId = new ReservationId();
            roomRepository.findById(roomNumber)
                    .ifPresent(reservationId::setRoom);
            reservationIdList.add(reservationId);
        }
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
