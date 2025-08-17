package myatt.abe.backend.controller;

import myatt.abe.backend.model.dto.ResultsDTO;
import myatt.abe.backend.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {

    private static final Logger logger = LoggerFactory.getLogger(CarController.class);
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    /**
     * Retrieves car data from the database.
     * Takes query parameters used for filtering the data.
     * @param make  The make of the car, e.g. Audi, BMW, Volkswagen
     * @param model The model of the car, e.g. A4, M3, Golf
     * @param bodyType  The body type of the car, e.g. Coupe, Sedan, SUV
     * @param doors     The number of doors the car has, e.g. 3, 4, 5
     * @param fuelType  The fuel type of the car, e.g. Petrol, Diesel, Electric
     * @param transmission  The transmission of the car, e.g. Automatic, Manual
     * @return  A list of cars based on the filters, containing different trims, engine sizes, etc.
     */
    @GetMapping("/cars")
    public ResponseEntity<List<ResultsDTO>> getCars(
            @RequestParam(value = "make", required = false) String make,
            @RequestParam(value = "model", required = false) String model,
            @RequestParam(value = "bodyType", required = false) String bodyType,
            @RequestParam(value = "doors", required = false) Integer doors,
            @RequestParam(value = "fuelType", required = false) String fuelType,
            @RequestParam(value = "transmission", required = false) String transmission) {
        logger.info("Request received by controller with params: make: {}, model: {}, body type: {}," +
                " doors: {}, fuel type: {}, transmission: {}", make, model, bodyType, doors, fuelType, transmission);

        var cars = carService.getCars(make, model, bodyType, doors, fuelType, transmission);

        return ResponseEntity.ok(cars);
    }

}
