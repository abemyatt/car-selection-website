package myatt.abe.backend.repository;

import myatt.abe.backend.model.entity.Body;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CarRepository extends JpaRepository<Body, Integer>, JpaSpecificationExecutor<Body> {

}
