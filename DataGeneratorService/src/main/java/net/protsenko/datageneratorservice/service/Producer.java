package net.protsenko.datageneratorservice.service;

import net.protsenko.core.model.VehicleData;

public interface Producer {

    void send(VehicleData vehicleData);

}
