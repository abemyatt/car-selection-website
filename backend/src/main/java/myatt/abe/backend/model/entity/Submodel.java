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
@Table(name = "submodels")
public class Submodel {

    @Id
    private Integer submodelId;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    private Integer modelYear;
    private String submodelName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "submodel")
    private List<Trim> trims;

    public Integer getSubmodelId() {
        return submodelId;
    }

    public void setSubmodelId(Integer submodelId) {
        this.submodelId = submodelId;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public String getSubmodelName() {
        return submodelName;
    }

    public void setSubmodelName(String submodelName) {
        this.submodelName = submodelName;
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

    public List<Trim> getTrims() {
        return trims;
    }

    public void setTrims(List<Trim> trims) {
        this.trims = trims;
    }
}

