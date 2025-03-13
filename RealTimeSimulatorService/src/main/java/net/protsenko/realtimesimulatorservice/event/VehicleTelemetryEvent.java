package net.protsenko.realtimesimulatorservice.event;

public record VehicleTelemetryEvent(
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
