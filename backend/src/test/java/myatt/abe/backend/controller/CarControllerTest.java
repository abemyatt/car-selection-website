package myatt.abe.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import myatt.abe.backend.model.dto.ResultsDTO;
import myatt.abe.backend.service.CarService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CarService carService;

    @Test
    void testGetCarsNoParamsReturnsAllCars() throws Exception {
        ResultsDTO car1 = new ResultsDTO("Audi", "A4", "Sedan", 4,
                "Petrol", "Automatic", 2.0, "Premium");
        ResultsDTO car2 = new ResultsDTO("BMW", "M3", "Coupe", 2,
                "Petrol", "Manual", 1.8, "Sport");
        doReturn(List.of(car1, car2))
                .when(carService)
                .getCars(isNull(), isNull(), isNull(), isNull(), isNull(), isNull());

        mockMvc.perform(get("/api/cars")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].makeName").value("Audi"))
                .andExpect(jsonPath("$[1].makeName").value("BMW"));
    }

    @Test
    void testGetCarsWithAllParamsReturnsFilteredCars() throws Exception {
        ResultsDTO car = new ResultsDTO("Audi", "A4", "Sedan", 4, "Petrol", "Automatic", 2.0, "Premium");
        doReturn(List.of(car))
                .when(carService)
                .getCars(eq("Audi"), eq("A4"), eq("Sedan"), eq(4), eq("Petrol"), eq("Automatic"));


        mockMvc.perform(get("/api/cars")
                        .param("make", "Audi")
                        .param("model", "A4")
                        .param("bodyType", "Sedan")
                        .param("doors", "4")
                        .param("fuelType", "Petrol")
                        .param("transmission", "Automatic")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].makeName").value("Audi"))
                .andExpect(jsonPath("$[0].modelName").value("A4"))
                .andExpect(jsonPath("$[0].bodyType").value("Sedan"))
                .andExpect(jsonPath("$[0].bodyDoors").value(4))
                .andExpect(jsonPath("$[0].engineFuelType").value("Petrol"))
                .andExpect(jsonPath("$[0].engineTransmission").value("Automatic"))
                .andExpect(jsonPath("$[0].engineSize").value(2.0))
                .andExpect(jsonPath("$[0].trimName").value("Premium"));
    }

    @Test
    void testGetCarsEmptyResultReturnsEmptyList() throws Exception {
        doReturn(List.of())
                .when(carService)
                .getCars(any(), any(), any(), any(), any(), any());

        mockMvc.perform(get("/api/cars")
                        .param("make", "Nonexistent")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
