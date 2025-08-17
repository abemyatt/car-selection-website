package myatt.abe.backend.service;

import myatt.abe.backend.mapper.CarMapper;
import myatt.abe.backend.model.dto.CarDTO;
import myatt.abe.backend.model.entity.Body;
import myatt.abe.backend.repository.CarRepository;
import myatt.abe.backend.repository.spec.CarSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarService {

    private static final Logger logger = LoggerFactory.getLogger(CarService.class);

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Transactional(readOnly = true)
    public List<CarDTO> getCars(String make, String model, String body, Integer doors, String fuelType, String transmission) {
        logger.info("searching for all cars with make: {}, model: {}, body: {}, doors: {}. fuel type: {}, transmission: {}",
                make, model, body, doors, fuelType, transmission);

        Specification<Body> spec = CarSpecification.fetchAllRelations()
                .and(CarSpecification.hasMake(make)
                .and(CarSpecification.hasModel(model))
                .and(CarSpecification.hasBodyType(body))
                .and(CarSpecification.hasDoors(doors))
                .and(CarSpecification.hasFuelType(fuelType))
                .and(CarSpecification.hasTransmission(transmission)));

        List<Body> bodies = carRepository.findAll(spec);

        logger.info("found {} records", bodies.size());

        return bodies.stream().map(CarMapper::toCarDTO).toList();
    }
}
