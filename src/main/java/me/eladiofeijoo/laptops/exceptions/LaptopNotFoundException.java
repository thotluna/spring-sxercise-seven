package me.eladiofeijoo.laptops.exceptions;

public class LaptopNotFoundException extends RuntimeException{
    public LaptopNotFoundException(Long id) {
        super("Could not find Laptop with id: " + id);
    }
}
