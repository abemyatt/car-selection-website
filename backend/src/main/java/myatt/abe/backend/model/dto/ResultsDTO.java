package myatt.abe.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a stripped down version of the car containing only the query / filtering data that a user might want to see.
 */
@Getter
@Setter
@AllArgsConstructor
public class ResultsDTO {

    private String makeName;
    private String modelName;
    private String bodyType;
    private Integer bodyDoors;
    private String engineFuelType;
    private String engineTransmission;
    private Double engineSize;
    private String trimName;

}
