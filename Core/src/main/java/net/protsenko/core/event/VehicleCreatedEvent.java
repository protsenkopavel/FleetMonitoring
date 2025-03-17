package net.protsenko.core.event;

import net.protsenko.core.model.VehicleData;

public record VehicleCreatedEvent(
        String vehicleId,
        VehicleData vehicleData
) {
}
