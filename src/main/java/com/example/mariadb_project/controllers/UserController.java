package com.example.mariadb_project.controllers;

import com.example.mariadb_project.dtos.UserRequestDTO;
import com.example.mariadb_project.dtos.UserUpdateRequestDTO;
import com.example.mariadb_project.models.User;
import com.example.mariadb_project.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create a new user
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        try {
            // Call the service to create the user
            User createdUser = userService.createUser(userRequestDTO);
            return ResponseEntity.ok(createdUser);
        } catch (IllegalArgumentException e) {
            // Return a bad request response if email is already in use
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint to get a user by email (for validation/testing purposes)
    @GetMapping("/getByEmail")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email)
                .map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.notFound().build());
    }

    // Update an existing user by ID
    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateUser(
            @PathVariable UUID userId,
            @Valid @RequestBody UserUpdateRequestDTO userUpdateRequest) {

        try {
            // Call the service to update the user
            User updatedUser = userService.updateUser(userId, userUpdateRequest);
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException e) {
            // Return a bad request response if there are validation errors
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Get a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Delete a user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRequestDTO loginRequest) {
        String response = userService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}
