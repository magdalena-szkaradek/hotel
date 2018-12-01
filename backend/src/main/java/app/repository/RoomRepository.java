package app.repository;

import app.entity.Room;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Integer> {
    @Query("SELECT t from Room t where t.beds >= ?1")
    List<Room> findRoomsWithAccurateNumberOfBeds(Integer guests);

}
