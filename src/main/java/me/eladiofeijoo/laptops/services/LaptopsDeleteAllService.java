package me.eladiofeijoo.laptops.services;

import me.eladiofeijoo.laptops.data.LaptopsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LaptopsDeleteAllService {
    private final LaptopsRepository repository;
    private final Logger log = LoggerFactory.getLogger(LaptopsSaveService.class);

    public LaptopsDeleteAllService(LaptopsRepository repository) {
        this.repository = repository;
    }

    public void execute(){
        repository.deleteAll();
    }
}
