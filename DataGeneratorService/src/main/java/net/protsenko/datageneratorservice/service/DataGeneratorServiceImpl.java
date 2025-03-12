package net.protsenko.datageneratorservice.service;

import lombok.RequiredArgsConstructor;
import net.protsenko.datageneratorservice.model.VehicleData;
import net.protsenko.uiserivce.dto.CarSettings;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataGeneratorServiceImpl implements DataGeneratorService {

    private final Producer producer;

    public void generateData(CarSettings carSettings) {
        List<VehicleData> vehicles = new ArrayList<>();

        for (int i = 0; i < carSettings.carCount(); i++) {
            VehicleData vehicle = new VehicleData();
            vehicle.generateData();
            vehicles.add(vehicle);

            producer.send(vehicle);
        }
    }

}
