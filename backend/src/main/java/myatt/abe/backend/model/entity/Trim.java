package myatt.abe.backend.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "trims")
public class Trim {

    @Id
    @Column(name = "trim_id")
    private Integer trimId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submodel_id", nullable = false)
    private Submodel submodel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @Column(name = "model_year", nullable = false)
    private Integer modelYear;

    @Column(name = "trim_name")
    private String trimName;

    @Column(name = "trim_description")
    private String trimDescription;

    @Column(name = "trim_msrp")
    private Double trimMsrp;

    @Column(name = "trim_invoice")
    private Double trimInvoice;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "trim", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Body> bodies;

    @OneToMany(mappedBy = "trim", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Engine> engines;

    @OneToMany(mappedBy = "trim", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Mileage> mileages;

}
