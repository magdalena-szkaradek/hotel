package app.repository;
import app.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("SELECT t from Reservation t where t.user.user_id =?1 ")
    List<Reservation> findAllByUserId(Integer userId);

    @Query("SELECT t from Reservation t where (t.startDate BETWEEN ?1 and ?2 OR t.endDate between ?1 and ?2)")
    List<Reservation> getRoomsNotAvailable(LocalDate startDate, LocalDate endDate);
}