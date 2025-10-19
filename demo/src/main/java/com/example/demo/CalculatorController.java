package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class CalculatorController {
    @GetMapping("/calculate")
    public String getMethodName(@RequestParam("a") double a,  // extracts query parameters from the URL, like /calculate?a=2&b=3&op=add
                                @RequestParam("b") double b,
                                @RequestParam("op") String op) {
        double result;
        switch (op) {
            case "add": result = a + b; break;
            case "subtract": result = a - b; break;
            case "multiply": result = a * b; break;
            case "divide": 
                if (b == 0) return "Error: Division by zero";
                result = a / b; break;
            default: return "Error: Invalid operation";
        }
        return "Result: " + result;
    }
}
