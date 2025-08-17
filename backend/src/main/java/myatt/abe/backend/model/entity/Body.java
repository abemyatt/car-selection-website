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
@Table(name = "bodies")
public class Body {

    @Id
    @Column(name = "body_id")
    private Integer bodyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trim_id", nullable = false)
    private Trim trim;

    @Column(name = "body_type")
    private String bodyType;

    @Column(name = "body_doors")
    private Integer bodyDoors;

    @Column(name = "body_seats")
    private Integer bodySeats;

    @Column(name = "body_length")
    private Double bodyLength;

    @Column(name = "body_width")
    private Double bodyWidth;

    @Column(name = "body_height")
    private Double bodyHeight;

    @Column(name = "body_wheel_base")
    private Double bodyWheelBase;

    @Column(name = "body_front_track")
    private Double bodyFrontTrack;

    @Column(name = "body_rear_track")
    private Double bodyRearTrack;

    @Column(name = "body_ground_clearance")
    private Double bodyGroundClearance;

    @Column(name = "body_cargo_capacity")
    private Double bodyCargoCapacity;

    @Column(name = "body_max_cargo_capacity")
    private Double bodyMaxCargoCapacity;

    @Column(name = "body_curb_weight")
    private Double bodyCurbWeight;

    @Column(name = "body_gross_weight")
    private Double bodyGrossWeight;

    @Column(name = "body_max_payload")
    private Double bodyMaxPayload;

    @Column(name = "body_max_towing_capacity")
    private Double bodyMaxTowingCapacity;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
