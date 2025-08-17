package myatt.abe.backend.mapper;

import myatt.abe.backend.model.dto.CarDTO;
import myatt.abe.backend.model.entity.Body;
import myatt.abe.backend.model.entity.Make;
import myatt.abe.backend.model.entity.Model;
import myatt.abe.backend.model.entity.Submodel;
import myatt.abe.backend.model.entity.Trim;

import java.util.Optional;

public class CarMapper {

    public static CarDTO toCarDTO(Body body) {
        CarDTO dto = new CarDTO();

        dto.setBodyType(body.getBodyType());
        dto.setBodyDoors(body.getBodyDoors());
        dto.setBodySeats(body.getBodySeats());
        dto.setBodyLength(body.getBodyLength());
        dto.setBodyWidth(body.getBodyWidth());
        dto.setBodyHeight(body.getBodyHeight());
        dto.setBodyWheelBase(body.getBodyWheelBase());
        dto.setBodyFrontTrack(body.getBodyFrontTrack());
        dto.setBodyRearTrack(body.getBodyRearTrack());
        dto.setBodyGroundClearance(body.getBodyGroundClearance());
        dto.setBodyCargoCapacity(body.getBodyCargoCapacity());
        dto.setBodyMaxCargoCapacity(body.getBodyMaxCargoCapacity());
        dto.setBodyCurbWeight(body.getBodyCurbWeight());
        dto.setBodyGrossWeight(body.getBodyGrossWeight());
        dto.setBodyMaxPayload(body.getBodyMaxPayload());
        dto.setBodyMaxTowingCapacity(body.getBodyMaxTowingCapacity());


        Trim trim = body.getTrim();
        if (trim != null) {
            dto.setTrimName(trim.getTrimName());
            dto.setTrimModelYear(trim.getModelYear());

            Submodel submodel = trim.getSubmodel();
            if (submodel != null) {
                dto.setSubmodelName(submodel.getSubmodelName());

                Model model = submodel.getModel();
                if (model != null) {
                    dto.setModelName(model.getModelName());

                    Make make = model.getMake();
                    if (make != null) {
                        dto.setMakeName(make.getMakeName());
                    }
                }
            }

            // Engine (take first engine if multiple)
            Optional.ofNullable(trim.getEngines()).flatMap(list -> list.stream().findFirst()).ifPresent(engine -> {
                dto.setEngineType(engine.getEngineType());
                dto.setEngineFuelType(engine.getEngineFuelType());
                dto.setEngineCylinders(engine.getEngineCylinders());
                dto.setEngineSize(engine.getEngineSize());
                dto.setEngineHorsepowerHp(engine.getEngineHorsepowerHp());
                dto.setEngineHorsepowerRpm(engine.getEngineHorsepowerRpm());
                dto.setEngineTorqueFtLbs(engine.getEngineTorqueFtLbs());
                dto.setEngineTorqueRpm(engine.getEngineTorqueRpm());
                dto.setEngineValves(engine.getEngineValves());
                dto.setEngineValveTiming(engine.getEngineValveTiming());
                dto.setEngineCamType(engine.getEngineCamType());
                dto.setEngineDriveType(engine.getEngineDriveType());
                dto.setEngineTransmission(engine.getEngineTransmission());
            });

            // Mileage (take first mileage if multiple)
            Optional.ofNullable(trim.getMileages()).flatMap(list -> list.stream().findFirst()).ifPresent(mileage -> {
                dto.setFuelTankCapacity(mileage.getFuelTankCapacity());
                dto.setCombinedMpg(mileage.getCombinedMpg());
                dto.setCityMpg(mileage.getCityMpg());
                dto.setHighwayMpg(mileage.getHighwayMpg());
                dto.setRangeCity(mileage.getRangeCity());
                dto.setRangeHighway(mileage.getRangeHighway());
                dto.setEvCombinedMpg(mileage.getEvCombinedMpg());
                dto.setEvCityMpg(mileage.getEvCityMpg());
                dto.setEvHighwayMpg(mileage.getEvHighwayMpg());
                dto.setEvRange(mileage.getEvRange());
                dto.setEvKwhPer100Mi(mileage.getEvKwhPer100Mi());
                dto.setEvChargeTimeHr240(mileage.getEvChargeTimeHr240());
                dto.setEvBatteryCapacity(mileage.getEvBatteryCapacity());
            });
        }

        return dto;
    }
}
