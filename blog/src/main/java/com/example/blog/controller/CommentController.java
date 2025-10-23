package com.example.blog.controller;

import com.example.blog.dto.CommentDTO;
import com.example.blog.model.BlogPost;
import com.example.blog.model.Comment;
import com.example.blog.repository.BlogPostRepository;
import com.example.blog.repository.CommentRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {

    private final BlogPostRepository postRepo;
    private final CommentRepository commentRepo;

    public CommentController(BlogPostRepository postRepo, CommentRepository commentRepo) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
    }

    @PostMapping
    public Comment create(@PathVariable Long postId, @Valid @RequestBody CommentDTO dto) {
        BlogPost post = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        Comment comment = new Comment();
        comment.setAuthor(dto.getAuthor());
        comment.setText(dto.getText());
        comment.setPost(post);
        return commentRepo.save(comment);
    }

    @GetMapping
    public Page<Comment> list(@PathVariable Long postId, Pageable pageable) {
        return commentRepo.findByPostId(postId, pageable);
    }

    @PutMapping("/{commentId}")
    public Comment update(@PathVariable Long postId, @PathVariable Long commentId,
                          @Valid @RequestBody CommentDTO dto) {
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        comment.setAuthor(dto.getAuthor());
        comment.setText(dto.getText());
        return commentRepo.save(comment);
    }

    @DeleteMapping("/{commentId}")
    public void delete(@PathVariable Long postId, @PathVariable Long commentId) {
        commentRepo.deleteById(commentId);
    }
}
