package com.example.notes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TagDTO {
    @NotBlank(message = "Tag name is mandatory")
    @Size(max = 50, message = "Tag name can't exceed 50 characters")
    private String name;

    // getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
