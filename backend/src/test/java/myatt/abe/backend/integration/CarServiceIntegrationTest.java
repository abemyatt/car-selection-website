package myatt.abe.backend.integration;


import myatt.abe.backend.model.dto.ResultsDTO;
import myatt.abe.backend.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Uses testcontainers to run an in-memory postgres db.
 * The schema and tables are created with src/test/resources/init-test-db.sql
 * This is then populated with test data from src/test/resources/init-test-data.sql
 * These tests assert that the car service correctly passes parameters
 * and the interaction with the db provides correct results.
 */
@SpringBootTest
@Testcontainers
@Transactional
class CarServiceIntegrationTest {

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test")
            .withInitScripts("init-test-db.sql", "init-test-data.sql");

    @Autowired
    private CarService carService;

    @Autowired
    private EntityManager entityManager;

    @DynamicPropertySource
    static void setDatasourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Test
    void testGetCarsByMake() {
        List<ResultsDTO> results = carService.getCars("Audi", null, null, null, null, null);

        assertThat(results).isNotEmpty();
        assertThat(results).allMatch(car -> car.getMakeName().equals("Audi"));
    }

    @Test
    void testGetCarsByModelYear() {
        List<ResultsDTO> results = carService.getCars(null, null, "Sedan", null, null, null);

        assertThat(results).isNotEmpty();
        assertThat(results).allMatch(car -> Objects.equals(car.getBodyType(), "Sedan"));
    }

    @Test
    void testGetCarsByFuelType() {
        List<ResultsDTO> results = carService.getCars(null, null, null, null, "Petrol", null);

        assertThat(results).isNotEmpty();
        assertThat(results).allMatch(car -> car.getEngineFuelType().equals("Petrol"));
    }

    @Test
    void testGetCarsByDoors() {
        List<ResultsDTO> results = carService.getCars(null, null, null, 4, null, null);

        assertThat(results).isNotEmpty();
        assertThat(results).allMatch(car -> car.getBodyDoors() == 4);
    }

    @Test
    void testGetCarsWithAllParameters() {
        List<ResultsDTO> results = carService.getCars(
                "Audi",
                "A4",
                "Sedan",
                4,
                "Petrol",
                "Automatic"
        );

        assertThat(results).isNotEmpty();
        assertThat(results.size()).isEqualTo(2);
        assertThat(results).allSatisfy(car -> {
            assertThat(car.getMakeName()).isEqualTo("Audi");
            assertThat(car.getModelName()).isEqualTo("A4");
            assertThat(car.getBodyType()).isEqualTo("Sedan");
            assertThat(car.getBodyDoors()).isEqualTo(4);
            assertThat(car.getEngineFuelType()).isEqualTo("Petrol");
            assertThat(car.getEngineTransmission()).isEqualTo("Automatic");
        });
    }

    @Test
    void testGetCarsWithNoParameters() {
        List<ResultsDTO> results = carService.getCars(
                null, null, null, null, null, null
        );

        assertThat(results).isNotEmpty();
        assertThat(results.size()).isEqualTo(12);

        assertThat(results).anyMatch(car -> car.getMakeName().equals("Audi"));
        assertThat(results).anyMatch(car -> car.getMakeName().equals("BMW"));
        assertThat(results).anyMatch(car -> car.getMakeName().equals("Volkswagen"));
    }

}
