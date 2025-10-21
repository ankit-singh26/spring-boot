package com.example.notes.controller;

import com.example.notes.model.Tag;
import com.example.notes.repository.TagRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    private final TagRepository tagRepository;

    public TagController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @PostMapping
    public Tag create(@RequestBody Tag tag) {
        return tagRepository.save(tag);
    }

    @GetMapping
    public List<Tag> getAll() {
        return tagRepository.findAll();
    }
}
