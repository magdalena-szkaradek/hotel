package app.service;

import app.entity.SeasonPrice;
import app.repository.SeasonPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeasonPriceService {
    @Autowired
    SeasonPriceRepository seasonPriceRepository;


    public Iterable<SeasonPrice> getAllSeasonPrices() {
        return seasonPriceRepository.findAll();
    }

    public SeasonPrice addNewSeasonPrice(SeasonPrice seasonPrice) {
        return seasonPriceRepository.save(seasonPrice);
    }

    public void deleteSeasonPrice(Integer id) {
        seasonPriceRepository.deleteById(id);
    }
}
