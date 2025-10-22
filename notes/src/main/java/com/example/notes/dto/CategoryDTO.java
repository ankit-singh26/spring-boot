package com.example.notes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryDTO {
    @NotBlank(message = "Category name is mandatory")
    @Size(max = 100, message = "Category name can't exceed 100 characters")
    private String name;

    // getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
