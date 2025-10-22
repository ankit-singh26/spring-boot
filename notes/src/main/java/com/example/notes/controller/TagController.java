package com.example.notes.controller;

import com.example.notes.dto.TagDTO;
import com.example.notes.model.Tag;
import com.example.notes.repository.TagRepository;

import jakarta.validation.Valid;
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
    public Tag createTag(@Valid @RequestBody TagDTO dto) {
        Tag tag = new Tag(dto.getName());
        return tagRepository.save(tag);
    }

    @GetMapping
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }
}
