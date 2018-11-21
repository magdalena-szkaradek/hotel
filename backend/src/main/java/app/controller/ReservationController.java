package app.controller;


import app.entity.Reservation;
import app.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path="/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @GetMapping("/getAll")
    public Iterable<Reservation> getAllReservations(){
        return reservationService.getAllReservations();
    }


    @PostMapping("/add")
    public ResponseEntity addNewReservation(@RequestBody Reservation reservation){
        return new ResponseEntity<>(reservationService.addNewReservation(reservation), HttpStatus.OK);
    }
}
