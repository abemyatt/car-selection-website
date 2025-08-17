package myatt.abe.backend.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import myatt.abe.backend.model.dto.ResultsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static myatt.abe.backend.constants.Constants.FILTER_JPQL;

@Service
public class CarService {

    private static final Logger logger = LoggerFactory.getLogger(CarService.class);

    @PersistenceContext
    private EntityManager entityManager;

    public List<ResultsDTO> getCars(
            String make,
            String model,
            String bodyType,
            Integer doors,
            String fuelType,
            String transmission) {

        logger.info("searching for all cars with make: {}, model: {}, body type: {}, doors: {}. fuel type: {}, transmission: {}",
                make, model, bodyType, doors, fuelType, transmission);

        var query = entityManager.createQuery(FILTER_JPQL, ResultsDTO.class);

        query.setParameter("makeName", make);
        query.setParameter("modelName", model);
        query.setParameter("bodyType", bodyType);
        query.setParameter("bodyDoors", doors);
        query.setParameter("engineFuelType", fuelType);
        query.setParameter("engineTransmission", transmission);

        var results = query.getResultList();
        logger.info("found {} records", results.size());

        return results;
    }
}
