package net.protsenko.realtimesimulatorservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.protsenko.core.model.VehicleData;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class VehicleSimulatorImpl implements VehicleSimulator {

    private final RealTimeSimulatorProducer realTimeSimulatorProducer;
    private final Map<String, VehicleData> vehicles = new ConcurrentHashMap<>();

    public void addVehicle(String vehicleId, VehicleData vehicleData) {
        vehicles.put(vehicleId, vehicleData);
    }

    public VehicleData getVehicleData(String vehicleId) {
        return vehicles.get(vehicleId);
    }

    @Override
    @Scheduled(fixedRate = 1000)
    public void updateTelemetryVehiclesData() {
        vehicles.forEach((vehicleId, vehicleData) -> {
            vehicleData.generateTelemetryData();
            log.info("Updated vehicleId {} data: {}", vehicleId, vehicleData);
            realTimeSimulatorProducer.sendTelemetry(vehicleId, vehicleData);
        });
    }

    @Override
    @Scheduled(fixedRate = 10000)
    public void updateCriticalVehiclesData() {
        vehicles.forEach((vehicleId, vehicleData) -> {
            vehicleData.generateCriticalData();
            log.info("Updated vehicleId {} data: {}", vehicleId, vehicleData);
            realTimeSimulatorProducer.sendCriticalData(vehicleId, vehicleData);
        });
    }

}
