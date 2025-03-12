package net.protsenko.realtimesimulatorservice.service;

import net.protsenko.datageneratorservice.model.VehicleData;

public interface VehicleSimulator {

    public void addVehicle(String vehicleId, VehicleData vehicleData);

    public VehicleData getVehicleData(String vehicleId);

    public void updateVehicles();

}
