package app.repository;
import app.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("SELECT t from Reservation t where t.user.user_id =?1 ")
    List<Reservation> findAllByUserId(Integer userId);
}