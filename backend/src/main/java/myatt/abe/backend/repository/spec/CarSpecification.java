package myatt.abe.backend.repository.spec;

import jakarta.persistence.criteria.JoinType;
import myatt.abe.backend.model.entity.Body;
import org.springframework.data.jpa.domain.Specification;

import static myatt.abe.backend.constants.Constants.ENGINES;
import static myatt.abe.backend.constants.Constants.MAKE;
import static myatt.abe.backend.constants.Constants.MODEL;
import static myatt.abe.backend.constants.Constants.SUBMODEL;
import static myatt.abe.backend.constants.Constants.TRIM;

public class CarSpecification {

    public static Specification<Body> fetchAllRelations() {
        return (root, query, cb) -> {
            if (query.getResultType() != Long.class) {
                var trimFetch = root.fetch(TRIM, JoinType.LEFT);
                var submodelFetch = trimFetch.fetch(SUBMODEL, JoinType.LEFT);
                var modelFetch = submodelFetch.fetch(MODEL, JoinType.LEFT);
                modelFetch.fetch(MAKE, JoinType.LEFT);

                trimFetch.fetch(ENGINES, JoinType.LEFT);
            }
            return cb.conjunction();
        };
    }

    public static Specification<Body> hasMake(String makeName) {
        return (root, query, cb) -> {
            if (makeName == null) return cb.conjunction();
            var trimJoin = root.join(TRIM);
            var submodelJoin = trimJoin.join(SUBMODEL);
            var modelJoin = submodelJoin.join(MODEL);
            var makeJoin = modelJoin.join(MAKE);
            return cb.equal(makeJoin.get("makeName"), makeName);
        };
    }

    public static Specification<Body> hasModel(String modelName) {
        return (root, query, cb) -> {
            if (modelName == null) return cb.conjunction();
            var trimJoin = root.join(TRIM);
            var submodelJoin = trimJoin.join(SUBMODEL);
            var modelJoin = submodelJoin.join(MODEL);
            return cb.equal(modelJoin.get("modelName"), modelName);
        };
    }

    public static Specification<Body> hasBodyType(String bodyType) {
        return (root, query, cb) -> {
            if (bodyType == null) return cb.conjunction();
            return cb.equal(root.get("bodyType"), bodyType);
        };
    }

    public static Specification<Body> hasDoors(Integer doors) {
        return (root, query, cb) -> {
            if (doors == null) return cb.conjunction();
            return cb.equal(root.get("bodyDoors"), doors);
        };
    }

    public static Specification<Body> hasFuelType(String fuelType) {
        return (root, query, cb) -> {
            if (fuelType == null) return cb.conjunction();
            var trimJoin = root.join(TRIM);
            var engineJoin = trimJoin.join(ENGINES, JoinType.LEFT);
            return cb.equal(engineJoin.get("engineFuelType"), fuelType);
        };
    }

    public static Specification<Body> hasTransmission(String transmission) {
        return (root, query, cb) -> {
            if (transmission == null) return cb.conjunction();
            var trimJoin = root.join(TRIM);
            var engineJoin = trimJoin.join(ENGINES, JoinType.LEFT);
            return cb.equal(engineJoin.get("engineTransmission"), transmission);
        };
    }
}
