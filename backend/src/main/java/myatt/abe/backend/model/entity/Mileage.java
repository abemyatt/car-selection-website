package myatt.abe.backend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "mileages")
public class Mileage {

    @Id
    @Column(name = "mileage_id")
    private Integer mileageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trim_id", nullable = false)
    private Trim trim;

    @Column(name = "fuel_tank_capacity")
    private Double fuelTankCapacity;

    @Column(name = "combined_mpg")
    private Double combinedMpg;

    @Column(name = "city_mpg")
    private Double cityMpg;

    @Column(name = "highway_mpg")
    private Double highwayMpg;

    @Column(name = "range_city")
    private Integer rangeCity;

    @Column(name = "range_highway")
    private Integer rangeHighway;

    @Column(name = "ev_combined_mpg")
    private Double evCombinedMpg;

    @Column(name = "ev_city_mpg")
    private Double evCityMpg;

    @Column(name = "ev_highway_mpg")
    private Double evHighwayMpg;

    @Column(name = "ev_range")
    private Integer evRange;

    @Column(name = "ev_kwh_per_100_mi")
    private Double evKwhPer100Mi;

    @Column(name = "ev_charge_time_hr_240")
    private Double evChargeTimeHr240;

    @Column(name = "ev_battery_capacity")
    private Double evBatteryCapacity;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
