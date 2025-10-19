package com.example.taskmanager.controllers;

import com.example.taskmanager.model.Task;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    private static List<Task> tasks = new ArrayList<>();
    private long nextId = 1;
    
    // Get all tasks
    @GetMapping
    public List<Task> getTasks() {
        return tasks;
    }

    // Get task by id
    @GetMapping("/{id}")
    public Task getTaskByID(@PathVariable long id) {
        return tasks.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }
    
    // Create a new task
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        task.setId(nextId++);
        tasks.add(task);
        return task;
    }

    // Update a task
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable long id, @RequestBody Task updatedTask) {
        Optional<Task> existingTaskOpt = tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst();

        if (existingTaskOpt.isPresent()) {
            Task existingTask = existingTaskOpt.get();
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            return existingTask;
        }
        return null;
    }

    // Delete a task
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable("id") long id) {
        boolean removed = tasks.removeIf(task -> task.getId() == id);
        if (removed) {
            return "Task with id " + id + " deleted.";
        }
        return "Task not found.";
    }
}
