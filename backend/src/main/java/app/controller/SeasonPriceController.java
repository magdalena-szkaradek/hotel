package app.controller;

import app.entity.SeasonPrice;
import app.service.SeasonPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping(path="/seasonPrice")
public class SeasonPriceController {

    @Autowired
    SeasonPriceService seasonPriceService;

    @GetMapping("/getAll")
    public Iterable<SeasonPrice> getAllSeasonPrices() {
        return seasonPriceService.getAllSeasonPrices();
    }

    @PostMapping("/add")
    public ResponseEntity addNewSeasonPrice(@RequestBody SeasonPrice seasonPrice){
        return new ResponseEntity<>(seasonPriceService.addNewSeasonPrice(seasonPrice), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSeasonPrice(@PathVariable Integer id){
        seasonPriceService.deleteSeasonPrice(id);
    }
}
