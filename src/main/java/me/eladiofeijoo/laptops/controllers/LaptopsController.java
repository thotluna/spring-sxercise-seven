package me.eladiofeijoo.laptops.controllers;

import me.eladiofeijoo.laptops.models.Laptop;
import me.eladiofeijoo.laptops.services.LaptopByIdService;
import me.eladiofeijoo.laptops.services.LaptopUpdateService;
import me.eladiofeijoo.laptops.services.LaptopsListService;
import me.eladiofeijoo.laptops.services.LaptopsSaveService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LaptopsController {
    private final LaptopsSaveService saveService;
    private final LaptopsListService listService;
    private final LaptopByIdService byIdService;
    private final LaptopUpdateService updateService;

    public LaptopsController(LaptopsSaveService saveService,
                             LaptopsListService listService,
                             LaptopByIdService byIdService,
                             LaptopUpdateService updateService) {
        this.saveService = saveService;
        this.listService = listService;
        this.byIdService = byIdService;
        this.updateService = updateService;
    }

    @PostMapping("/v1/laptops")
    public Laptop add(@RequestBody Laptop laptop){
        return saveService.execute(laptop);
    }

    @GetMapping("/v1/laptops")
    public List<Laptop> getAll(){
        return listService.execute();
    }

    @GetMapping("/v1/laptops/{id}")
    public Laptop getById(@PathVariable Long id){
        return byIdService.execute(id);
    }

    @PutMapping("/v1/laptops")
    public Laptop update(@RequestBody Laptop laptop){
        return updateService.execute(laptop);
    }
}
