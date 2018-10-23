package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    public Iterable<Room> getAllRooms() {
        System.out.println(roomRepository.findAll());
        return roomRepository.findAll();
    }
}
