package com.example.blog.controller;

import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;
import com.example.blog.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authManager;
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authManager, UserRepository userRepo,
                          PasswordEncoder encoder, JwtUtil jwtUtil) {
        this.authManager = authManager;
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    @Operation(
        summary = "Register a new user account.",
        description = "Creates a new user with an encoded password and returns confirmation or an error.",
        requestBody = @RequestBody(
            required = true,
            description = "User details (username and password). Password must meet any policy configured.",
            content = @Content(schema = @Schema(implementation = User.class))
        ),
        responses = {
            @ApiResponse(responseCode = "200", description = "Signup successful"),
            @ApiResponse(responseCode = "400", description = "Username already exists or validation failed")
        }
    )
    @PostMapping("/signup")
    public String signup(@Valid @RequestBody User user) {
        if (userRepo.existsByUsername(user.getUsername())) {
            return "Username already exists!";
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        return "Signup successful!";
    }

    @Operation(
        summary = "Authenticate a user and receive a JWT token.",
        description = "Validates username and password. Returns a JWT token for authenticated API usage.",
        requestBody = @RequestBody(
            required = true,
            description = "Credentials for login.",
            content = @Content(schema = @Schema(
                    example = "{\"username\": \"user1\", \"password\": \"Password123\"}"
            ))
        ),
        responses = {
            @ApiResponse(responseCode = "200", description = "Returns a JWT token in JSON: { \"JWT\": \"...\" }"),
            @ApiResponse(responseCode = "401", description = "Invalid username or password")
        }
    )
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.get("username"), request.get("password"))
        );
        String token = jwtUtil.generateToken(request.get("username"));
        return Map.of("JWT", token);
    }
}
