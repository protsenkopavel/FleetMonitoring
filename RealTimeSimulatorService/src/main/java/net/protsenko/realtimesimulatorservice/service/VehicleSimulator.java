package net.protsenko.realtimesimulatorservice.service;

import net.protsenko.datageneratorservice.model.VehicleData;

public interface VehicleSimulator {

    void addVehicle(String vehicleId, VehicleData vehicleData);

    VehicleData getVehicleData(String vehicleId);

    void updateTelemetryVehiclesData();

    void updateCriticalVehiclesData();

}
