package com.example.taskmanager.service;

import com.example.taskmanager.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    
    public static List<User> users = new ArrayList<>();
    public long nextId = 1;
    public BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User register(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(nextId++, username, encodedPassword);
        users.add(user);
        return user;
    }

    public boolean login(String username, String password) {
        return users.stream().filter(u->u.getUsername().equals(username) && passwordEncoder.matches(password, u.getPassword())).findFirst().isPresent();
    }

    public List<User> getUsers() {
        return users;
    }
}
