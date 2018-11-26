package app.controller;


import app.entity.Reservation;
import app.entity.ReservationDTO;
import app.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping(path="/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @GetMapping("/getAll")
    public Iterable<Reservation> getAllReservations(){
        return reservationService.getAllReservations();
    }


    @PostMapping("/add")
    public ResponseEntity addNewReservation(@RequestBody ReservationDTO reservation){
        return new ResponseEntity<>(reservationService.addNewReservation(reservation), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void removeReservation(@PathVariable("id") Integer reservationId){
        reservationService.deleteReservation(reservationId);
    }

}
