package net.protsenko.realtimesimulatorservice.event;

public record CriticalDataEvent(
        double tirePressure,
        boolean airbagActivated
) {
}
