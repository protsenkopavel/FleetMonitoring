package net.protsenko.uiserivce.dto;

public record CarSettings(
        int carCount,
        double maxRadius,
        double fuelThreshold,
        double tirePressureThreshold
) {
}
