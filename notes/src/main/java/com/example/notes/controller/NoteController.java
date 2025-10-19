package com.example.notes.controller;

import com.example.notes.model.Note;
import com.example.notes.repository.NoteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/notes")
public class NoteController {
    
    private final NoteRepository noteRepository;
    
    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
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
    public Note createNote(@RequestBody Note note){
        return noteRepository.save(note);
    }

    @PutMapping("/{id}")
    public Note UpdateNote(@PathVariable Long id, @RequestBody Note noteDetails){
        Note note = noteRepository.findById(id).orElse(null);
        if(note != null){
            note.setTitle(noteDetails.getTitle());
            note.setContent(noteDetails.getContent());
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
