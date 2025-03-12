package net.protsenko.datageneratorservice.service;

import net.protsenko.datageneratorservice.model.VehicleData;

public interface Producer {

    void send(VehicleData vehicleData);

}
