package me.eladiofeijoo.laptops.services;

import me.eladiofeijoo.laptops.data.LaptopsRepository;
import me.eladiofeijoo.laptops.models.Laptop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaptopsListService {
    private final LaptopsRepository repository;
    private final Logger log = LoggerFactory.getLogger(LaptopsSaveService.class);

    public LaptopsListService(LaptopsRepository repository) {
        this.repository = repository;
    }

    public List<Laptop> execute(){
        return repository.findAll();
    }
}
