package net.protsenko.datageneratorservice.controller;

import lombok.RequiredArgsConstructor;
import net.protsenko.datageneratorservice.service.DataGeneratorService;
import net.protsenko.uiserivce.dto.CarSettings;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/data-generator/")
public class DataGeneratorController {

    private final DataGeneratorService dataGeneratorService;

    @PostMapping("generate/")
    public void generateData(@RequestBody CarSettings carSettings) {
        dataGeneratorService.generateData(carSettings);
    }

}
