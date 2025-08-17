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
@Table(name = "engines")
public class Engine {

    @Id
    @Column(name = "engine_id")
    private Integer engineId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trim_id", nullable = false)
    private Trim trim;

    @Column(name = "engine_type")
    private String engineType;

    @Column(name = "engine_fuel_type")
    private String engineFuelType;

    @Column(name = "engine_cylinders")
    private String engineCylinders;

    @Column(name = "engine_size")
    private Double engineSize;

    @Column(name = "engine_horsepower_hp")
    private Integer engineHorsepowerHp;

    @Column(name = "engine_horsepower_rpm")
    private Integer engineHorsepowerRpm;

    @Column(name = "engine_torque_ft_lbs")
    private Integer engineTorqueFtLbs;

    @Column(name = "engine_torque_rpm")
    private Integer engineTorqueRpm;

    @Column(name = "engine_valves")
    private Integer engineValves;

    @Column(name = "engine_valve_timing")
    private String engineValveTiming;

    @Column(name = "engine_cam_type")
    private String engineCamType;

    @Column(name = "engine_drive_type")
    private String engineDriveType;

    @Column(name = "engine_transmission")
    private String engineTransmission;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
