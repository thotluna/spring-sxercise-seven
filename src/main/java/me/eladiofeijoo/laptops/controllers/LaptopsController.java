package me.eladiofeijoo.laptops.controllers;

import me.eladiofeijoo.laptops.models.Laptop;
import me.eladiofeijoo.laptops.services.LaptopsListService;
import me.eladiofeijoo.laptops.services.LaptopsSaveService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LaptopsController {
    private final LaptopsSaveService saveService;
    private final LaptopsListService listService;

    public LaptopsController(LaptopsSaveService saveService, LaptopsListService listService) {
        this.saveService = saveService;
        this.listService = listService;
    }

    @PostMapping("/v1/laptops")
    public Laptop add(@RequestBody Laptop laptop){
        return saveService.execute(laptop);
    }

    @GetMapping("/v1/laptops")
    public List<Laptop> getAll(){
        return listService.execute();
    }
}
