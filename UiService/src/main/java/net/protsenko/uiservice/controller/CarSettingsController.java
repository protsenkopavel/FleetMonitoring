package net.protsenko.uiservice.controller;

import lombok.RequiredArgsConstructor;
import net.protsenko.core.dto.CarSettings;
import net.protsenko.uiservice.service.CarSettingsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/ui/")
public class CarSettingsController {

    private final CarSettingsService carSettingsService;

    @GetMapping("car-settings/")
    public String getCarSettings(Model model) {
        model.addAttribute("carSettings", carSettingsService.getCarSettings());
        return "carSettings";
    }

    @PostMapping("car-settings/")
    public String updateCarSettings(@ModelAttribute CarSettings updatedSettings) {
        carSettingsService.updateCarSettings(updatedSettings);
        return "redirect:/api/v1/ui/car-settings/";
    }

}
