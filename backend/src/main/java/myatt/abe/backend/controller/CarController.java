package myatt.abe.backend.controller;

import myatt.abe.backend.model.dto.CarDTO;
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

    @GetMapping("/cars")
    public ResponseEntity<List<CarDTO>> getCars(
            @RequestParam(value = "make", required = false) String make,
            @RequestParam(value = "model", required = false) String model,
            @RequestParam(value = "body", required = false) String body,
            @RequestParam(value = "doors", required = false) Integer doors,
            @RequestParam(value = "fuel-type", required = false) String fuelType,
            @RequestParam(value = "transmission", required = false) String transmission) {

        var cars = carService.getCars(make, model, body, doors, fuelType, transmission);

        return ResponseEntity.ok(cars);
    }

}
