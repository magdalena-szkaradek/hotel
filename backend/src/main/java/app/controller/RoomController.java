package app.controller;

import app.entity.Room;
import app.entity.SearchCriteriaDTO;
import app.entity.SearchResultsDTO;
import app.service.RoomService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping(path = "/room")
public class RoomController {

    @Autowired
    RoomService roomService;

    @GetMapping("/getAll")
    public Iterable<Room> getAllRoomInfo() {

        return roomService.getAllRooms();
    }

    @PostMapping("/add")
    public ResponseEntity addNewRoom(@RequestBody Room room) {
        return new ResponseEntity<>(roomService.addNewRoom(room), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRoom(@PathVariable Integer id) {
        roomService.deleteRoom(id);
    }

    @PostMapping("/searchBy")
    public SearchResultsDTO searchByCriteria(@RequestBody SearchCriteriaDTO searchCriteriaDTO) {
        return roomService.searchByCriteria(searchCriteriaDTO);
    }

    @GetMapping("/getRoomById/{id}")
    public Optional<Room> findRoom(@PathVariable Integer id){
        return roomService.findRoomById(id);
    }
}
