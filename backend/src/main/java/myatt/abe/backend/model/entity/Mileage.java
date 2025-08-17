package myatt.abe.backend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "mileages")
public class Mileage {

    @Id
    private Integer mileageId;

    @ManyToOne
    @JoinColumn(name = "trim_id")
    private Trim trim;

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
    @Column(name = "ev_kwh_per_100_mi")
    private Double evKwhPer100Mi;
    @Column(name = "ev_charge_time_hr_240")
    private Double evChargeTimeHr240;
    private Double evBatteryCapacity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Integer getMileageId() {
        return mileageId;
    }

    public void setMileageId(Integer mileageId) {
        this.mileageId = mileageId;
    }

    public Trim getTrim() {
        return trim;
    }

    public void setTrim(Trim trim) {
        this.trim = trim;
    }

    public Double getFuelTankCapacity() {
        return fuelTankCapacity;
    }

    public void setFuelTankCapacity(Double fuelTankCapacity) {
        this.fuelTankCapacity = fuelTankCapacity;
    }

    public Double getCombinedMpg() {
        return combinedMpg;
    }

    public void setCombinedMpg(Double combinedMpg) {
        this.combinedMpg = combinedMpg;
    }

    public Double getCityMpg() {
        return cityMpg;
    }

    public void setCityMpg(Double cityMpg) {
        this.cityMpg = cityMpg;
    }

    public Double getHighwayMpg() {
        return highwayMpg;
    }

    public void setHighwayMpg(Double highwayMpg) {
        this.highwayMpg = highwayMpg;
    }

    public Integer getRangeCity() {
        return rangeCity;
    }

    public void setRangeCity(Integer rangeCity) {
        this.rangeCity = rangeCity;
    }

    public Integer getRangeHighway() {
        return rangeHighway;
    }

    public void setRangeHighway(Integer rangeHighway) {
        this.rangeHighway = rangeHighway;
    }

    public Double getEvCombinedMpg() {
        return evCombinedMpg;
    }

    public void setEvCombinedMpg(Double evCombinedMpg) {
        this.evCombinedMpg = evCombinedMpg;
    }

    public Double getEvCityMpg() {
        return evCityMpg;
    }

    public void setEvCityMpg(Double evCityMpg) {
        this.evCityMpg = evCityMpg;
    }

    public Double getEvHighwayMpg() {
        return evHighwayMpg;
    }

    public void setEvHighwayMpg(Double evHighwayMpg) {
        this.evHighwayMpg = evHighwayMpg;
    }

    public Integer getEvRange() {
        return evRange;
    }

    public void setEvRange(Integer evRange) {
        this.evRange = evRange;
    }

    public Double getEvKwhPer100Mi() {
        return evKwhPer100Mi;
    }

    public void setEvKwhPer100Mi(Double evKwhPer100Mi) {
        this.evKwhPer100Mi = evKwhPer100Mi;
    }

    public Double getEvChargeTimeHr240() {
        return evChargeTimeHr240;
    }

    public void setEvChargeTimeHr240(Double evChargeTimeHr240) {
        this.evChargeTimeHr240 = evChargeTimeHr240;
    }

    public Double getEvBatteryCapacity() {
        return evBatteryCapacity;
    }

    public void setEvBatteryCapacity(Double evBatteryCapacity) {
        this.evBatteryCapacity = evBatteryCapacity;
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

