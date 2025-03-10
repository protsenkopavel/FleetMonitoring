package net.protsenko.datageneratorservice.event;

import net.protsenko.datageneratorservice.model.VehicleData;

public record VehicleCreatedEvent(
        String vehicleId,
        VehicleData vehicleData
) {
}
