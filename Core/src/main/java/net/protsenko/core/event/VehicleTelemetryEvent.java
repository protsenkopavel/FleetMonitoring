package net.protsenko.core.event;

public record VehicleTelemetryEvent(
        String id,
        double latitude,
        double longitude,
        double speed,
        double fuelLevel,
        double engineTemperature,
        double mileage,
        long engineRuntime,
        int doorStatus
) {
}
