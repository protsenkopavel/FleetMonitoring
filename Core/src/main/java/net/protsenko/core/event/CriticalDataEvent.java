package net.protsenko.core.event;

public record CriticalDataEvent(
        double tirePressure,
        boolean airbagActivated
) {
}
