package net.protsenko.realtimesimulatorservice.service;

import net.protsenko.core.model.VehicleData;

public interface RealTimeSimulatorProducer {

    void sendTelemetry(String vehicleId, VehicleData vehicleData);

    void sendCriticalData(String vehicleId, VehicleData vehicleData);

}