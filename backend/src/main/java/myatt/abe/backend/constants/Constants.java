package myatt.abe.backend.constants;

public class Constants {
    public static final String FILTER_JPQL = """
            SELECT new myatt.abe.backend.model.dto.ResultsDTO(
                mk.makeName,
                m.modelName,
                b.bodyType,
                b.bodyDoors,
                e.engineFuelType,
                e.engineTransmission,
                e.engineSize,
                t.trimName
            )
            FROM Trim t
            JOIN t.model m
            JOIN m.make mk
            LEFT JOIN t.bodies b
            LEFT JOIN t.engines e
            WHERE (:makeName IS NULL OR mk.makeName = :makeName)
              AND (:modelName IS NULL OR m.modelName = :modelName)
              AND (:bodyType IS NULL OR b.bodyType = :bodyType)
              AND (:bodyDoors IS NULL OR b.bodyDoors = :bodyDoors)
              AND (:engineFuelType IS NULL OR e.engineFuelType = :engineFuelType)
              AND (:engineTransmission IS NULL OR e.engineTransmission = :engineTransmission)
            """;
}
