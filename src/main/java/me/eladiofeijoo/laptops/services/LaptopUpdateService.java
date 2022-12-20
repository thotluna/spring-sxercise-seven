package me.eladiofeijoo.laptops.services;

import me.eladiofeijoo.laptops.data.LaptopsRepository;
import me.eladiofeijoo.laptops.exceptions.LaptopBadRequest;
import me.eladiofeijoo.laptops.exceptions.LaptopNotFoundException;
import me.eladiofeijoo.laptops.models.Laptop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LaptopUpdateService {

    private final LaptopsRepository repository;
    private final Logger log = LoggerFactory.getLogger(LaptopsSaveService.class);

    public LaptopUpdateService(LaptopsRepository repository) {
        this.repository = repository;
    }

    public Laptop execute(Laptop laptop){

        Laptop newLaptop = validate(laptop);

        if(laptop.getManufacturer() != null) newLaptop.setManufacturer(laptop.getManufacturer());
        if(laptop.getInches() != null) newLaptop.setInches(laptop.getInches());
        if(laptop.getRam() != null) newLaptop.setRam(laptop.getRam());

        return repository.save(newLaptop);
    }

    public Laptop validate(Laptop laptop){

        if(laptop.getId() == null){
            log.warn("laptop has no id ");
            throw new LaptopBadRequest("unsupported laptop");
        }

        Optional<Laptop> optionalLaptop = repository.findById(laptop.getId());
        if(optionalLaptop.isEmpty()){
            log.warn("Not Found find by id: " + laptop.getId());
            throw new LaptopNotFoundException(laptop.getId());
        }
        return optionalLaptop.get();
    }


}
