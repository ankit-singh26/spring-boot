package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // Easily handles web requests and return data
public class HelloController {
    @GetMapping("/")  // Maps HTTP GET requests at the root URL to this method
    public String getMethodName() {
        return "Hello World";
    }
}
