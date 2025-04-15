package com.chilluminati.chillstock;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloSpring {
    @GetMapping("/hello")
    public String test() {
        return "HelloSpring";
    }
}