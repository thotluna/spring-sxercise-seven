package me.eladiofeijoo.laptops.services;

import me.eladiofeijoo.laptops.data.LaptopsRepository;
import me.eladiofeijoo.laptops.exceptions.BadRequest;
import me.eladiofeijoo.laptops.models.Laptop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LaptopSaveService {
    private final LaptopsRepository repository;
    private final Logger log = LoggerFactory.getLogger(LaptopSaveService.class);

    public LaptopSaveService(LaptopsRepository repository) {
        this.repository = repository;
    }

    public Laptop execute(Laptop laptop) throws BadRequest{
        assert laptop != null;

        if(validate(laptop)) {
            String message = "Please, validate information sent";
            log.warn(message);
            throw new BadRequest(message);
        }
        return repository.save(laptop);
    }

    private boolean validate(Laptop laptop){
        return laptop.getId() != null || laptop.getManufacturer() == null || laptop.getInches() == null || laptop.getRam() == null;
    }
}
