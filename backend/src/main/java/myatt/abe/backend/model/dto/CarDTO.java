package myatt.abe.backend.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDTO {

    private String bodyType;
    private Integer bodyDoors;
    private Integer bodySeats;
    private Double bodyLength;
    private Double bodyWidth;
    private Double bodyHeight;
    private Double bodyWheelBase;
    private Double bodyFrontTrack;
    private Double bodyRearTrack;
    private Double bodyGroundClearance;
    private Double bodyCargoCapacity;
    private Double bodyMaxCargoCapacity;
    private Double bodyCurbWeight;
    private Double bodyGrossWeight;
    private Double bodyMaxPayload;
    private Double bodyMaxTowingCapacity;

    private String engineType;
    private String engineFuelType;
    private String engineCylinders;
    private Double engineSize;
    private Integer engineHorsepowerHp;
    private Integer engineHorsepowerRpm;
    private Integer engineTorqueFtLbs;
    private Integer engineTorqueRpm;
    private Integer engineValves;
    private String engineValveTiming;
    private String engineCamType;
    private String engineDriveType;
    private String engineTransmission;

    private Double fuelTankCapacity;
    private Double combinedMpg;
    private Double cityMpg;
    private Double highwayMpg;
    private Integer rangeCity;
    private Integer rangeHighway;
    private Double evCombinedMpg;
    private Double evCityMpg;
    private Double evHighwayMpg;
    private Integer evRange;
    private Double evKwhPer100Mi;
    private Double evChargeTimeHr240;
    private Double evBatteryCapacity;

    private String trimName;
    private String trimDescription;
    private Double trimMsrp;
    private Double trimInvoice;
    private Integer trimModelYear;

    private String submodelName;
    private Integer submodelYear;

    private String modelName;
    private String makeName;
}
