package app.repository;
import app.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("SELECT t from Reservation t where t.user.user_id =?1 ")
    List<Reservation> findAllByUserId(Integer userId);

    @Query("SELECT t from Reservation t where (?1 BETWEEN t.startDate and t.endDate OR ?2 BETWEEN t.startDate and t.endDate) OR " +
            "?1 <= t.startDate AND ?2 >= t.startDate")
    List<Reservation> getRoomsNotAvailable(LocalDate startDate, LocalDate endDate);
}