package com.example.blog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO used for creating or updating a blog post.")
public class BlogPostDTO {

    @NotBlank(message = "Title is required")
    @Schema(description = "The title of the blog post", example = "Awesome Spring Boot Guide", required = true)
    private String title;

    @NotBlank(message = "Content is required")
    @Schema(description = "Main content/body of the blog post", example = "Here is a practical Spring Boot tutorial...", required = true)
    private String content;

    // Getters and setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
