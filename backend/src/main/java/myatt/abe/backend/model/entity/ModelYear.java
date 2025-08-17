package myatt.abe.backend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "model_years")
public class ModelYear {

    @Id
    @Column(name = "model_id")
    private Integer id;

    // Actual field mapped to DB column
    @Column(name = "model_year")
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "model_year", insertable = false, updatable = false)
    private Model model;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
