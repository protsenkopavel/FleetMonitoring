package net.protsenko.datageneratorservice.model;

import lombok.Getter;

@Getter
public class VehicleData {

    private double latitude;
    private double longitude;
    private double speed;
    private double fuelLevel;
    private double tirePressure;
    private double engineTemperature;
    private double mileage;
    private long engineRuntime;
    private int doorStatus;
    private boolean airbagActivated;

    public void generateData() {
        this.latitude = generateLatitude();
        this.longitude = generateLongitude();
        this.speed = generateSpeed();
        this.fuelLevel = generateFuelLevel();
        this.tirePressure = generateTirePressure();
        this.engineTemperature = generateEngineTemperature();
        this.mileage = generateMileage();
        this.engineRuntime = generateEngineRuntime();
        this.doorStatus = generateDoorStatus();
        this.airbagActivated = generateAirbagData();
    }

    private double generateLatitude() {
        return 55.0 + Math.random();
    }

    private double generateLongitude() {
        return 37.0 + Math.random();
    }

    private double generateSpeed() {
        return Math.random() * 120;
    }

    private double generateFuelLevel() {
        return Math.random() * 100;
    }

    private double generateTirePressure() {
        return 2 + Math.random();
    }

    private double generateEngineTemperature() {
        return 70 + Math.random() * 40;
    }

    private double generateMileage() {
        return Math.random() * 100000;
    }

    private long generateEngineRuntime() {
        return (long) (Math.random() * 3600);
    }

    private int generateDoorStatus() {
        return Math.random() > 0.5 ? 1 : 0;
    }

    private boolean generateAirbagData() {
        return Math.random() < 0.1;
    }

}
