package net.protsenko.uiserivce.controller;

import net.protsenko.core.dto.CarSettings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/api/v1/ui/")
public class CarSettingsController {

    private final RestTemplate restTemplate = new RestTemplate();

    private CarSettings carSettings = new CarSettings(10, 15.0, 20.0, 30.0);

    @GetMapping("car-settings/")
    public String getCarSettings(Model model) {
        model.addAttribute("carSettings", carSettings);
        return "carSettings";
    }

    @PostMapping("car-settings/")
    public String updateCarSettings(@ModelAttribute CarSettings updatedSettings) {
        this.carSettings = updatedSettings;

        String dataGeneratorUrl = "http://data-generator-service:8081/api/v1/data-generator/generate/";
        restTemplate.postForLocation(dataGeneratorUrl, updatedSettings);

        return "redirect:/api/v1/ui/car-settings/";
    }

}
