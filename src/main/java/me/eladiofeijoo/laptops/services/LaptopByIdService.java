package me.eladiofeijoo.laptops.services;

import me.eladiofeijoo.laptops.data.LaptopsRepository;
import me.eladiofeijoo.laptops.exceptions.LaptopNotFoundException;
import me.eladiofeijoo.laptops.models.Laptop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LaptopByIdService {

    private final LaptopsRepository repository;
    private final Logger log = LoggerFactory.getLogger(LaptopByIdService.class);

    public LaptopByIdService(LaptopsRepository repository) {
        this.repository = repository;
    }

    public Laptop execute(Long id){
        Optional<Laptop> res = repository.findById(id);
        if(res.isPresent()){
            return res.get();
        }else{
            errorNotFound(id);
        }
        return null;
    }

    private void errorNotFound(Long id){
        log.warn("Not Found find by id: " + id);
        throw new LaptopNotFoundException(id);
    }
}
