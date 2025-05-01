package net.protsenko.uiservice.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.protsenko.core.dto.CarSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CarSettingsService {

    private final RestTemplate restTemplate;

    @Value("${data.generator.url}")
    private String dataGeneratorUrl;

    @Getter
    private CarSettings carSettings = new CarSettings(10, 15.0, 20.0, 30.0);

    public void updateCarSettings(CarSettings updatedSettings) {
        this.carSettings = updatedSettings;
        try {
            restTemplate.postForLocation(dataGeneratorUrl, updatedSettings);
        } catch (Exception e) {
            System.err.println("Error calling data generator: " + e.getMessage());
        }
    }
}