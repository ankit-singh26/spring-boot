package com.example.blog.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Schema(description = "Represents a blog post with comments.")
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Database ID of the blog post", example = "1")
    private Long id;

    @NotBlank
    @Schema(description = "Title of the blog post", example = "Spring Tips & Tricks")
    private String title;

    @Column(length = 2048)
    @Schema(description = "Main content of the blog post", example = "Today we explore advanced Spring tips...")
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Schema(description = "Comments under the blog post")
    private List<Comment> comments = new ArrayList<>();

    public BlogPost() {}

    public BlogPost(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {   
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
