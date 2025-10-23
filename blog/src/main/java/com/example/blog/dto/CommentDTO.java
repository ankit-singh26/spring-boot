package com.example.blog.dto;

import jakarta.validation.constraints.NotBlank;

public class CommentDTO {
    @NotBlank(message = "Author is required")
    private String author;

    @NotBlank(message = "Text is required")
    private String text;

    // Getters and setters
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}
