package app.repository;

import app.entity.SeasonPrice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface SeasonPriceRepository extends CrudRepository<SeasonPrice, Integer> {

    @Query("select t from SeasonPrice t where (?1 BETWEEN t.startDate and t.endDate OR ?2 BETWEEN t.startDate and t.endDate) OR " +
            "?1 <= t.startDate AND ?2 >= t.startDate")
    List<SeasonPrice> findApplicableSeasoningPrices(LocalDate startDate, LocalDate endDate);
}