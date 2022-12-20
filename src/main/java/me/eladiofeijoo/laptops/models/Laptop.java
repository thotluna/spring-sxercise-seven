package me.eladiofeijoo.laptops.models;

import javax.persistence.*;

@Entity
@Table(name = "laptops")
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String manufacturer;
    private Integer inches;
    private Integer ram;

    public Laptop() {
    }

    public Laptop(Long id, String manufacturer, Integer inches, Integer ram) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.inches = inches;
        this.ram = ram;
    }

    public Long getId() {
        return id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Integer getInches() {
        return inches;
    }

    public Integer getRam() {
        return ram;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setInches(Integer inches) {
        this.inches = inches;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }
}
