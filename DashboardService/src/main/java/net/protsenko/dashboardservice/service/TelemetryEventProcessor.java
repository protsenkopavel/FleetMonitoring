package net.protsenko.dashboardservice.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.protsenko.realtimesimulatorservice.event.VehicleTelemetryEvent;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelemetryEventProcessor {

    private final KStream<String, VehicleTelemetryEvent> telemetryStream;

    @PostConstruct
    public void startStreamProcessing() {
        telemetryStream.foreach((key, event) -> processEvent(event));
    }

    public void processSpeedEvent(VehicleTelemetryEvent event) {
        System.out.printf("Speed: %s%n", event.speed());
    }

    public void processCoordinatesEvent(VehicleTelemetryEvent event) {
        System.out.printf("Coordinates: Latitude: %s, Longitude: %s%n", event.latitude(), event.longitude());
    }

    public void processFuelLevelEvent(VehicleTelemetryEvent event) {
        System.out.printf("Fuel level: %s%n", event.fuelLevel());
    }

    public void processEngineTemperatureEvent(VehicleTelemetryEvent event) {
        System.out.printf("Engine Temperature: %s%n", event.engineTemperature());
    }

    public void processMileageEvent(VehicleTelemetryEvent event) {
        System.out.printf("Mileage: %s%n", event.mileage());
    }

    public void processEngineRuntimeEvent(VehicleTelemetryEvent event) {
        System.out.printf("Engine Runtime: %s%n", event.engineRuntime());
    }

    public void processDoorStatusEvent(VehicleTelemetryEvent event) {
        System.out.printf("Door Status: %s%n", event.doorStatus());
    }

    public void processEvent(VehicleTelemetryEvent event) {
        processSpeedEvent(event);
        processCoordinatesEvent(event);
        processFuelLevelEvent(event);
        processEngineTemperatureEvent(event);
        processMileageEvent(event);
        processEngineRuntimeEvent(event);
        processDoorStatusEvent(event);
    }
}
