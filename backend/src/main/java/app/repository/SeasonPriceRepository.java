package app.repository;

import app.entity.SeasonPrice;
import org.springframework.data.repository.CrudRepository;

public interface SeasonPriceRepository extends CrudRepository<SeasonPrice, Integer> {
}
