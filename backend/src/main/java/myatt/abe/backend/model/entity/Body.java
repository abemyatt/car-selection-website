package myatt.abe.backend.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "bodies")
public class Body {

    @Id
    private Integer bodyId;

    @ManyToOne
    @JoinColumn(name = "trim_id")
    private Trim trim;

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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Integer getBodyId() {
        return bodyId;
    }

    public void setBodyId(Integer bodyId) {
        this.bodyId = bodyId;
    }

    public Trim getTrim() {
        return trim;
    }

    public void setTrim(Trim trim) {
        this.trim = trim;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public Integer getBodyDoors() {
        return bodyDoors;
    }

    public void setBodyDoors(Integer bodyDoors) {
        this.bodyDoors = bodyDoors;
    }

    public Integer getBodySeats() {
        return bodySeats;
    }

    public void setBodySeats(Integer bodySeats) {
        this.bodySeats = bodySeats;
    }

    public Double getBodyLength() {
        return bodyLength;
    }

    public void setBodyLength(Double bodyLength) {
        this.bodyLength = bodyLength;
    }

    public Double getBodyWidth() {
        return bodyWidth;
    }

    public void setBodyWidth(Double bodyWidth) {
        this.bodyWidth = bodyWidth;
    }

    public Double getBodyHeight() {
        return bodyHeight;
    }

    public void setBodyHeight(Double bodyHeight) {
        this.bodyHeight = bodyHeight;
    }

    public Double getBodyWheelBase() {
        return bodyWheelBase;
    }

    public void setBodyWheelBase(Double bodyWheelBase) {
        this.bodyWheelBase = bodyWheelBase;
    }

    public Double getBodyFrontTrack() {
        return bodyFrontTrack;
    }

    public void setBodyFrontTrack(Double bodyFrontTrack) {
        this.bodyFrontTrack = bodyFrontTrack;
    }

    public Double getBodyRearTrack() {
        return bodyRearTrack;
    }

    public void setBodyRearTrack(Double bodyRearTrack) {
        this.bodyRearTrack = bodyRearTrack;
    }

    public Double getBodyGroundClearance() {
        return bodyGroundClearance;
    }

    public void setBodyGroundClearance(Double bodyGroundClearance) {
        this.bodyGroundClearance = bodyGroundClearance;
    }

    public Double getBodyCargoCapacity() {
        return bodyCargoCapacity;
    }

    public void setBodyCargoCapacity(Double bodyCargoCapacity) {
        this.bodyCargoCapacity = bodyCargoCapacity;
    }

    public Double getBodyMaxCargoCapacity() {
        return bodyMaxCargoCapacity;
    }

    public void setBodyMaxCargoCapacity(Double bodyMaxCargoCapacity) {
        this.bodyMaxCargoCapacity = bodyMaxCargoCapacity;
    }

    public Double getBodyCurbWeight() {
        return bodyCurbWeight;
    }

    public void setBodyCurbWeight(Double bodyCurbWeight) {
        this.bodyCurbWeight = bodyCurbWeight;
    }

    public Double getBodyGrossWeight() {
        return bodyGrossWeight;
    }

    public void setBodyGrossWeight(Double bodyGrossWeight) {
        this.bodyGrossWeight = bodyGrossWeight;
    }

    public Double getBodyMaxPayload() {
        return bodyMaxPayload;
    }

    public void setBodyMaxPayload(Double bodyMaxPayload) {
        this.bodyMaxPayload = bodyMaxPayload;
    }

    public Double getBodyMaxTowingCapacity() {
        return bodyMaxTowingCapacity;
    }

    public void setBodyMaxTowingCapacity(Double bodyMaxTowingCapacity) {
        this.bodyMaxTowingCapacity = bodyMaxTowingCapacity;
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
