package net.protsenko.dashboardservice.service;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.protsenko.dashboardservice.entity.TelemetryMetrics;
import net.protsenko.realtimesimulatorservice.event.VehicleTelemetryEvent;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelemetryEventProcessor {

    private final KStream<String, VehicleTelemetryEvent> telemetryStream;
    private final MeterRegistry meterRegistry;

    private final ConcurrentMap<String, TelemetryMetrics> metricsMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void startStreamProcessing() {
        telemetryStream.foreach((vehicleId, event) -> processEvent(vehicleId, event));
    }

    public void processEvent(String vehicleId, VehicleTelemetryEvent event) {
        log.info("Received event: {}", vehicleId);
        TelemetryMetrics metrics = metricsMap.computeIfAbsent(vehicleId, id -> {
            TelemetryMetrics newMetrics = new TelemetryMetrics();
            meterRegistry.gauge("vehicle_speed", Tags.of("vehicle", id), newMetrics, TelemetryMetrics::getSpeed);
            meterRegistry.gauge("vehicle_latitude", Tags.of("vehicle", id), newMetrics, TelemetryMetrics::getLatitude);
            meterRegistry.gauge("vehicle_longitude", Tags.of("vehicle", id), newMetrics, TelemetryMetrics::getLongitude);
            meterRegistry.gauge("vehicle_fuel_level", Tags.of("vehicle", id), newMetrics, TelemetryMetrics::getFuelLevel);
            meterRegistry.gauge("vehicle_engine_temperature", Tags.of("vehicle", id), newMetrics, TelemetryMetrics::getEngineTemperature);
            meterRegistry.gauge("vehicle_mileage", Tags.of("vehicle", id), newMetrics, TelemetryMetrics::getMileage);
            meterRegistry.gauge("vehicle_engine_runtime", Tags.of("vehicle", id), newMetrics, TelemetryMetrics::getEngineRuntime);
            meterRegistry.gauge("vehicle_door_status", Tags.of("vehicle", id), newMetrics, TelemetryMetrics::getDoorStatus);
            return newMetrics;
        });

        metrics.setSpeed(event.speed());
        metrics.setLatitude(event.latitude());
        metrics.setLongitude(event.longitude());
        metrics.setFuelLevel(event.fuelLevel());
        metrics.setEngineTemperature(event.engineTemperature());
        metrics.setMileage(event.mileage());
        metrics.setEngineRuntime(event.engineRuntime());
        metrics.setDoorStatus(event.doorStatus());
    }
}
