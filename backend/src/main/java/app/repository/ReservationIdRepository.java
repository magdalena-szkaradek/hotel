package app.repository;
import app.entity.Reservation;
import app.entity.ReservationId;
import org.springframework.data.repository.CrudRepository;

public interface ReservationIdRepository extends CrudRepository<ReservationId, Integer> {
}