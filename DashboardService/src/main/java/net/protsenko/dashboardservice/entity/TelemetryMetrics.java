package net.protsenko.dashboardservice.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TelemetryMetrics {

    private volatile double speed;
    private volatile double latitude;
    private volatile double longitude;
    private volatile double fuelLevel;
    private volatile double engineTemperature;
    private volatile double mileage;
    private volatile double engineRuntime;
    private volatile int doorStatus;

}
