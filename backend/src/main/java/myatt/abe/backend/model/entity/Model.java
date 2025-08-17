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
@Table(name = "models")
public class Model {

    @Id
    private Integer modelId;

    @ManyToOne
    @JoinColumn(name = "make_id")
    private Make make;

    private String modelName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "model")
    private List<Submodel> submodels;

    @JsonIgnore
    @OneToMany(mappedBy = "model")
    private List<ModelYear> modelYears;

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
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

    public List<Submodel> getSubmodels() {
        return submodels;
    }

    public void setSubmodels(List<Submodel> submodels) {
        this.submodels = submodels;
    }

    public List<ModelYear> getModelYears() {
        return modelYears;
    }

    public void setModelYears(List<ModelYear> modelYears) {
        this.modelYears = modelYears;
    }

    // getters & setters
}
