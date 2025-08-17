package myatt.abe.backend.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import myatt.abe.backend.constants.Constants;
import myatt.abe.backend.model.dto.MakeDTO;
import myatt.abe.backend.model.dto.ModelDTO;
import myatt.abe.backend.model.dto.ResultsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static myatt.abe.backend.constants.Constants.DISTINCT_MAKES_JPQL;
import static myatt.abe.backend.constants.Constants.FILTER_JPQL;
import static myatt.abe.backend.constants.Constants.MODELS_BY_MAKE_JPQL;

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

    public List<MakeDTO> getDistinctMakes() {
        logger.info("Fetching distinct makes");

        var query = entityManager.createQuery(DISTINCT_MAKES_JPQL, MakeDTO.class);

        var results = query.getResultList();
        logger.info("Found {} distinct makes", results.size());

        return results;
    }

    public List<ModelDTO> getModelsByMake(Integer makeId) {
        logger.info("Retrieving distinct models for make ID {}", makeId);
        var query = entityManager.createQuery(MODELS_BY_MAKE_JPQL, ModelDTO.class);
        query.setParameter("makeId", makeId);

        var results = query.getResultList();
        logger.info("Found {} distinct models for make ID {}", results.size(), makeId);

        return results;
    }

}
