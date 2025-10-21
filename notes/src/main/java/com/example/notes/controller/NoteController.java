package com.example.notes.controller;

import com.example.notes.model.Category;
import com.example.notes.model.Note;
import com.example.notes.model.Tag;
import com.example.notes.repository.CategoryRepository;
import com.example.notes.repository.NoteRepository;
import com.example.notes.repository.TagRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    
    private final NoteRepository noteRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    
    public NoteController(NoteRepository noteRepository, 
                          CategoryRepository categoryRepository,
                          TagRepository tagRepository) {
        this.noteRepository = noteRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
    }
    
    @GetMapping
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable Long id) {
        return noteRepository.findById(id).orElse(null);
    }
    
    @PostMapping
    public Note createNote(@RequestBody Note note) {
        // Set managed Category
        if (note.getCategory() != null && note.getCategory().getId() != null) {
            Category category = categoryRepository.findById(note.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
            note.setCategory(category);
        }

        // Set managed Tags
        if (note.getTags() != null && !note.getTags().isEmpty()) {
            Set<Tag> managedTags = new HashSet<>();
            for (Tag tag : note.getTags()) {
                Tag managedTag = tagRepository.findById(tag.getId())
                    .orElseThrow(() -> new RuntimeException("Tag not found"));
                managedTags.add(managedTag);
            }
            note.setTags(managedTags);
        }

        return noteRepository.save(note);
    }
    
    @PutMapping("/{id}")
    public Note update(@PathVariable Long id, @RequestBody Note newNote) {
        Note note = noteRepository.findById(id).orElse(null);
        if (note != null) {
            note.setTitle(newNote.getTitle());
            note.setContent(newNote.getContent());

            // Set managed Category
            if (newNote.getCategory() != null && newNote.getCategory().getId() != null) {
                Category category = categoryRepository.findById(newNote.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
                note.setCategory(category);
            }

            // Set managed Tags
            if (newNote.getTags() != null && !newNote.getTags().isEmpty()) {
                Set<Tag> managedTags = new HashSet<>();
                for (Tag tag : newNote.getTags()) {
                    Tag managedTag = tagRepository.findById(tag.getId())
                        .orElseThrow(() -> new RuntimeException("Tag not found"));
                    managedTags.add(managedTag);
                }
                note.setTags(managedTags);
            }

            return noteRepository.save(note);
        }
        return null;
    }
    
    @DeleteMapping("/{id}")
    public String deleteNote(@PathVariable Long id) {
        noteRepository.deleteById(id);
        return "Note deleted successfully!";
    }
}
