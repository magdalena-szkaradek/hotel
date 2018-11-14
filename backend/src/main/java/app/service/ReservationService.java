package app.service;

import app.entity.Reservation;
import app.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    public Iterable<Reservation> getAllReservations(){
        return reservationRepository.findAll();
    }

    public Reservation addNewReservation(Reservation reservation){
        return reservationRepository.save(reservation);
    }
}


