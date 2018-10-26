package app.controller;

import app.entity.Room;
import app.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/room")
public class RoomController {

    @Autowired
    RoomService roomService;

    @RequestMapping("/getAll")
    public Iterable<Room> getAllRoomInfo() {

        return roomService.getAllRooms();
    }
}
