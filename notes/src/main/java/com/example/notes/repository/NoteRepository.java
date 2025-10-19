package com.example.notes.repository;

import com.example.notes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository gives you ready-made CRUD functions like findAll(), findById(), save(), and deleteById()

public interface NoteRepository extends JpaRepository<Note, Long> {
    
}
