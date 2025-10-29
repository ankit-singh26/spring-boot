package com.example.blog.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
@Schema(description = "Application user account credentials and role.")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique database ID for the user.", example = "1")
    private Long id;

    @NotBlank
    @Column(unique = true)
    @Schema(description = "Unique username for login and identification.", example = "admin")
    private String username;

    @NotBlank
    @Schema(description = "Hashed password for the user account (stored encrypted, never plaintext).", example = "$2a$10$...")
    private String password;

    @Schema(description = "Role indicating user type or permissions.", example = "USER")
    private String role = "USER";

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
