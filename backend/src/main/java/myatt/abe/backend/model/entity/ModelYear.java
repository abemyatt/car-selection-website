package myatt.abe.backend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "model_years")
@IdClass(ModelYearId.class)
public class ModelYear {

    @Id
    @Column(name = "model_id")
    private Integer modelId;

    @Id
    @Column(name = "model_year")
    private Integer modelYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", insertable = false, updatable = false)
    private Model model;

    @OneToMany(mappedBy = "modelYearReference", fetch = FetchType.LAZY)
    private List<Submodel> submodels;

}

class ModelYearId implements Serializable {
    private Integer modelId;
    private Integer modelYear;

    public ModelYearId() {
    }

    public ModelYearId(Integer modelId, Integer modelYear) {
        this.modelId = modelId;
        this.modelYear = modelYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModelYearId that)) return false;
        return Objects.equals(modelId, that.modelId) &&
                Objects.equals(modelYear, that.modelYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelId, modelYear);
    }
}
