package app.controller;

import app.entity.Reservation;
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
    public Iterable<Reservation> getAllReswervations(){
        return reservationService.getAllReservations();
    }

    @PostMapping("/add")
    public ResponseEntity addNewReservation(@RequestBody Reservation reservation){
        return new ResponseEntity<>(reservationService.addNewReservation(reservation), HttpStatus.OK);
    }
}


//@RestController
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
//@RequestMapping(path="/room")
//public class RoomController {
//
//    @Autowired
//    RoomService roomService;
//
//    @GetMapping("/getAll")
//    public Iterable<Room> getAllRoomInfo() {
//
//        return roomService.getAllRooms();
//    }
//
//    @PostMapping("/add")
//    public ResponseEntity addNewRoom(@RequestBody Room room){
//        return new ResponseEntity<>(roomService.addNewRoom(room), HttpStatus.OK);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public void deleteRoom(@PathVariable Integer id){
//        roomService.deleteRoom(id);
//    }
//}
