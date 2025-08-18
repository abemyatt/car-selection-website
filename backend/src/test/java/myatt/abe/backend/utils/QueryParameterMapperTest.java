package myatt.abe.backend.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class QueryParameterMapperTest {

    @Test
    void mapFuelTypeToBackendNullValueReturnsNull() {
        assertNull(QueryParameterMapper.mapFuelTypeToBackend(null));
    }

    @Test
    void mapFuelTypeToBackendPetrolReturnsUnleaded() {
        assertEquals("unleaded", QueryParameterMapper.mapFuelTypeToBackend("petrol"));
    }

    @Test
    void mapFuelTypeToBackendOtherValuesReturnAsIs() {
        assertEquals("diesel", QueryParameterMapper.mapFuelTypeToBackend("diesel"));
        assertEquals("electric", QueryParameterMapper.mapFuelTypeToBackend("electric"));
    }
}
