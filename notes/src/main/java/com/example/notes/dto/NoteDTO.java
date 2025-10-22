package com.example.notes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

public class NoteDTO {
    @NotBlank(message = "Title is mandatory")
    @Size(max = 100, message = "Title can't exceed 100 characters")
    private String title;

    @Size(max = 1000, message = "Content can't exceed 1000 characters")
    private String content;

    @NotNull(message = "Category is mandatory")
    private Long categoryId;

    private Set<Long> tagIds;

    // Getters and setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    public Set<Long> getTagIds() { return tagIds; }
    public void setTagIds(Set<Long> tagIds) { this.tagIds = tagIds; }
}
