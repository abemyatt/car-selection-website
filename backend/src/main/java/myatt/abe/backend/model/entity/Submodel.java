package myatt.abe.backend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "submodels")
public class Submodel {

    @Id
    @Column(name = "submodel_id")
    private Integer submodelId;

    @Column(name = "submodel_name", nullable = false)
    private String submodelName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "model_id", referencedColumnName = "model_id", nullable = false),
            @JoinColumn(name = "model_year", referencedColumnName = "model_year", nullable = false)
    })
    private ModelYear modelYearReference;

    @OneToMany(mappedBy = "submodel", fetch = FetchType.LAZY)
    private List<Trim> trims;

}
