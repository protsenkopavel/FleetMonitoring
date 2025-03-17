package net.protsenko.core.dto;

public record CarSettings(
        int carCount,
        double maxRadius,
        double fuelThreshold,
        double tirePressureThreshold
) {
}
