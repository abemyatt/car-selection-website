package myatt.abe.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "trims")
public class Trim {

    @Id
    private Integer trimId;

    @ManyToOne
    @JoinColumn(name = "submodel_id")
    private Submodel submodel;

    private Integer modelId;
    private Integer modelYear;
    private String trimName;
    private String trimDescription;
    private Double trimMsrp;
    private Double trimInvoice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "trim")
    private List<Body> bodies;

    @JsonIgnore
    @OneToMany(mappedBy = "trim")
    private List<Engine> engines;

    @JsonIgnore
    @OneToMany(mappedBy = "trim")
    private List<Mileage> mileages;

    public Integer getTrimId() {
        return trimId;
    }

    public void setTrimId(Integer trimId) {
        this.trimId = trimId;
    }

    public Submodel getSubmodel() {
        return submodel;
    }

    public void setSubmodel(Submodel submodel) {
        this.submodel = submodel;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public String getTrimName() {
        return trimName;
    }

    public void setTrimName(String trimName) {
        this.trimName = trimName;
    }

    public String getTrimDescription() {
        return trimDescription;
    }

    public void setTrimDescription(String trimDescription) {
        this.trimDescription = trimDescription;
    }

    public Double getTrimMsrp() {
        return trimMsrp;
    }

    public void setTrimMsrp(Double trimMsrp) {
        this.trimMsrp = trimMsrp;
    }

    public Double getTrimInvoice() {
        return trimInvoice;
    }

    public void setTrimInvoice(Double trimInvoice) {
        this.trimInvoice = trimInvoice;
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

    public List<Body> getBodies() {
        return bodies;
    }

    public void setBodies(List<Body> bodies) {
        this.bodies = bodies;
    }

    public List<Engine> getEngines() {
        return engines;
    }

    public void setEngines(List<Engine> engines) {
        this.engines = engines;
    }

    public List<Mileage> getMileages() {
        return mileages;
    }

    public void setMileages(List<Mileage> mileages) {
        this.mileages = mileages;
    }
}
