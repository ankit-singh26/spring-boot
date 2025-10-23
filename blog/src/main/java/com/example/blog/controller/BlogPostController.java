package com.example.blog.controller;

import com.example.blog.dto.BlogPostDTO;
import com.example.blog.model.BlogPost;
import com.example.blog.repository.BlogPostRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class BlogPostController {
    private final BlogPostRepository postRepo;

    public BlogPostController(BlogPostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @PostMapping
    public BlogPost create(@Valid @RequestBody BlogPostDTO dto) {
        BlogPost post = new BlogPost();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        return postRepo.save(post);
    }

    @GetMapping
    public Page<BlogPost> list(Pageable pageable) {
        return postRepo.findAll(pageable);
    }

    @GetMapping("/{id}")
    public BlogPost get(@PathVariable Long id) {
        return postRepo.findById(id)
               .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    @PutMapping("/{id}")
    public BlogPost update(@PathVariable Long id, @Valid @RequestBody BlogPostDTO dto) {
        BlogPost post = postRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        return postRepo.save(post);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        postRepo.deleteById(id);
    }
}
