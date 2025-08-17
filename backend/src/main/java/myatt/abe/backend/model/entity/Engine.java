package myatt.abe.backend.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "engines")
public class Engine {

    @Id
    private Integer engineId;

    @ManyToOne
    @JoinColumn(name = "trim_id")
    private Trim trim;

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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Integer getEngineId() {
        return engineId;
    }

    public void setEngineId(Integer engineId) {
        this.engineId = engineId;
    }

    public Trim getTrim() {
        return trim;
    }

    public void setTrim(Trim trim) {
        this.trim = trim;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getEngineFuelType() {
        return engineFuelType;
    }

    public void setEngineFuelType(String engineFuelType) {
        this.engineFuelType = engineFuelType;
    }

    public String getEngineCylinders() {
        return engineCylinders;
    }

    public void setEngineCylinders(String engineCylinders) {
        this.engineCylinders = engineCylinders;
    }

    public Double getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(Double engineSize) {
        this.engineSize = engineSize;
    }

    public Integer getEngineHorsepowerHp() {
        return engineHorsepowerHp;
    }

    public void setEngineHorsepowerHp(Integer engineHorsepowerHp) {
        this.engineHorsepowerHp = engineHorsepowerHp;
    }

    public Integer getEngineHorsepowerRpm() {
        return engineHorsepowerRpm;
    }

    public void setEngineHorsepowerRpm(Integer engineHorsepowerRpm) {
        this.engineHorsepowerRpm = engineHorsepowerRpm;
    }

    public Integer getEngineTorqueFtLbs() {
        return engineTorqueFtLbs;
    }

    public void setEngineTorqueFtLbs(Integer engineTorqueFtLbs) {
        this.engineTorqueFtLbs = engineTorqueFtLbs;
    }

    public Integer getEngineTorqueRpm() {
        return engineTorqueRpm;
    }

    public void setEngineTorqueRpm(Integer engineTorqueRpm) {
        this.engineTorqueRpm = engineTorqueRpm;
    }

    public Integer getEngineValves() {
        return engineValves;
    }

    public void setEngineValves(Integer engineValves) {
        this.engineValves = engineValves;
    }

    public String getEngineValveTiming() {
        return engineValveTiming;
    }

    public void setEngineValveTiming(String engineValveTiming) {
        this.engineValveTiming = engineValveTiming;
    }

    public String getEngineCamType() {
        return engineCamType;
    }

    public void setEngineCamType(String engineCamType) {
        this.engineCamType = engineCamType;
    }

    public String getEngineDriveType() {
        return engineDriveType;
    }

    public void setEngineDriveType(String engineDriveType) {
        this.engineDriveType = engineDriveType;
    }

    public String getEngineTransmission() {
        return engineTransmission;
    }

    public void setEngineTransmission(String engineTransmission) {
        this.engineTransmission = engineTransmission;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
