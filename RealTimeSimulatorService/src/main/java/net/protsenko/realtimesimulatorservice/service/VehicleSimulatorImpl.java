package net.protsenko.realtimesimulatorservice.service;

import lombok.extern.slf4j.Slf4j;
import net.protsenko.datageneratorservice.model.VehicleData;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class VehicleSimulatorImpl implements VehicleSimulator {

    private final Map<String, VehicleData> vehicles = new ConcurrentHashMap<>();

    public void addVehicle(String vehicleId, VehicleData vehicleData) {
        vehicles.put(vehicleId, vehicleData);
    }

    public VehicleData getVehicleData(String vehicleId) {
        return vehicles.get(vehicleId);
    }

    @Scheduled(fixedRate = 1000)
    public void updateVehicles() {
        vehicles.forEach((vehicleId, vehicleData) -> {
            vehicleData.generateData();
            log.info("Updated vehicleId {} data: {}", vehicleId, vehicleData.toString());
        });
    }

}
