package com.example.blog.controller;

import com.example.blog.dto.BlogPostDTO;
import com.example.blog.model.BlogPost;
import com.example.blog.repository.BlogPostRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@Tag(name = "Blog Post Management", description = "Endpoints for creating, reading, updating, and deleting blog posts")
@SecurityRequirement(name = "bearerAuth")
public class BlogPostController {
    private final BlogPostRepository postRepo;

    public BlogPostController(BlogPostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @Operation(summary = "Create a new blog post", description = "Requires JWT authentication.")
    @PostMapping
    public BlogPost create(@Valid @RequestBody BlogPostDTO dto) {
        BlogPost post = new BlogPost();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        return postRepo.save(post);
    }

    @Operation(summary = "List blog posts with pagination", description = "Returns a paginated list of blog posts. Requires JWT.")
    @GetMapping
    public Page<BlogPost> list(Pageable pageable) {
        return postRepo.findAll(pageable);
    }

    @Operation(summary = "Get a specific blog post by ID")
    @GetMapping("/{id}")
    public BlogPost get(
        @Parameter(description = "Blog post ID", example = "1")
        @PathVariable Long id) {
        return postRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    @Operation(summary = "Update a blog post by ID")
    @PutMapping("/{id}")
    public BlogPost update(
        @Parameter(description = "Blog post ID", example = "1")
        @PathVariable Long id,
        @Valid @RequestBody BlogPostDTO dto) {
        BlogPost post = postRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        return postRepo.save(post);
    }

    @Operation(summary = "Delete a blog post by ID")
    @DeleteMapping("/{id}")
    public void delete(
        @Parameter(description = "Blog post ID", example = "1")
        @PathVariable Long id) {
        postRepo.deleteById(id);
    }
}
