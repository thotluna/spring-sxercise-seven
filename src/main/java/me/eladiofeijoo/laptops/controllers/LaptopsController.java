package me.eladiofeijoo.laptops.controllers;

import me.eladiofeijoo.laptops.models.Laptop;
import me.eladiofeijoo.laptops.services.LaptopSaveService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LaptopsController {
    private final LaptopSaveService saveService;

    public LaptopsController(LaptopSaveService saveService) {
        this.saveService = saveService;
    }

    @PostMapping("/v1/laptops")
    public Laptop add(@RequestBody Laptop laptop){
        return saveService.execute(laptop);
    }
}
