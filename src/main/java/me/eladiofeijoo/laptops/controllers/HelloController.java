package me.eladiofeijoo.laptops.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String helloWord(){
        return "Hello Word!!";
    }
}
