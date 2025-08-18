package myatt.abe.backend.utils;

public final class QueryParameterMapper {

    private QueryParameterMapper() {
        // prevent instantiation of the utility class
    }

    public static String mapFuelTypeToBackend(String fuelType) {
        if (fuelType == null) return null;

        if (fuelType.equals("petrol")) return "unleaded";

        return fuelType;
    }
}
