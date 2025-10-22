package com.example.notes.controller;

import com.example.notes.dto.CategoryDTO;
import com.example.notes.model.Category;
import com.example.notes.repository.CategoryRepository;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping
    public Category createCategory(@Valid @RequestBody CategoryDTO dto) {
        Category category = new Category(dto.getName());
        return categoryRepository.save(category);
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
