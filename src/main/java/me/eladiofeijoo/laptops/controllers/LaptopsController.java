package me.eladiofeijoo.laptops.controllers;

import me.eladiofeijoo.laptops.models.Laptop;
import me.eladiofeijoo.laptops.services.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LaptopsController {
    private final LaptopsSaveService saveService;
    private final LaptopsListService listService;
    private final LaptopByIdService byIdService;
    private final LaptopUpdateService updateService;

    private final LaptopsDeleteAllService deleteAllService;

    public LaptopsController(LaptopsSaveService saveService,
                             LaptopsListService listService,
                             LaptopByIdService byIdService,
                             LaptopUpdateService updateService,
                             LaptopsDeleteAllService deleteAllService) {
        this.saveService = saveService;
        this.listService = listService;
        this.byIdService = byIdService;
        this.updateService = updateService;
        this.deleteAllService = deleteAllService;
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

    @DeleteMapping("/v1/laptops")
    public void deleteAll(){
        deleteAllService.execute();
    }
}
