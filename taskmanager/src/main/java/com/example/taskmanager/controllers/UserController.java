package com.example.taskmanager.controllers;

import com.example.taskmanager.dto.UserDTO;
import com.example.taskmanager.model.User;
import com.example.taskmanager.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO.getUsername(), userDTO.getPassword());
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO) {
        boolean success = userService.login(userDTO.getUsername(), userDTO.getPassword());
        return success ? "Login successful!" : "Invalid username or password.";
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getUsers();
    }
}
