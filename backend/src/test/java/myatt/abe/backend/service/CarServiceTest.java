package myatt.abe.backend.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import myatt.abe.backend.model.dto.ResultsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Due to the way the service is setup, this unit test class primarily tests interaction, and not output.
 * Integration tests cover testing with real data and an in-memory database.
 */
@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    private CarService carService;
    private EntityManager entityManager;

    @BeforeEach
    void setup() {
        carService = new CarService();
        entityManager = mock(EntityManager.class);
        ReflectionTestUtils.setField(carService, "entityManager", entityManager);
    }

    @Test
    void verifyCarServiceCallsEntityManagerWithParameters() {
        var mockData = setupMockData("Audi", "A4", "Sedan", 4, "Petrol", "Manual");
        var query = mock(TypedQuery.class);
        doReturn(query).when(entityManager).createQuery(anyString(), eq(ResultsDTO.class));
        doReturn(mockData).when(query).getResultList();

        var result = carService.getCars("Audi", "A4", "Sedan",
                4, "Petrol", "Manual");

        verify(entityManager).createQuery(anyString(), eq(ResultsDTO.class));
        verify(query).setParameter("makeName", "Audi");
        verify(query).setParameter("modelName", "A4");
        verify(query).setParameter("bodyType", "Sedan");
        verify(query).setParameter("bodyDoors", 4);
        verify(query).setParameter("engineFuelType", "Petrol");
        verify(query).setParameter("engineTransmission", "Manual");
        verify(query).getResultList();
    }

    @Test
    void verifyCarServiceCallsEntityManagerWithNoParameters() {
        var query = mock(TypedQuery.class);
        doReturn(query).when(entityManager).createQuery(anyString(), eq(ResultsDTO.class));
        doReturn(List.of()).when(query).getResultList();

        var result = carService.getCars("Audi", "A4", "Sedan",
                4, "Petrol", "Manual");

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(entityManager).createQuery(anyString(), eq(ResultsDTO.class));
        verify(query).setParameter("makeName", "Audi");
        verify(query).setParameter("modelName", "A4");
        verify(query).setParameter("bodyType", "Sedan");
        verify(query).setParameter("bodyDoors", 4);
        verify(query).setParameter("engineFuelType", "Petrol");
        verify(query).setParameter("engineTransmission", "Manual");
        verify(query).getResultList();
    }

    private List<ResultsDTO> setupMockData(String make, String model, String bodyType, Integer doors,
                                           String fuelType, String transmission) {
        var result = new ResultsDTO(make, model, bodyType, doors, fuelType, transmission, 2.0,
                "2.0 TFSI ultra Premium Plus");

        return List.of(result);
    }
}
