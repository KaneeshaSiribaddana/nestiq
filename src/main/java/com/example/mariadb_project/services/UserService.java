package com.example.mariadb_project.services;

import com.example.mariadb_project.dtos.UserRequestDTO;
import com.example.mariadb_project.dtos.UserUpdateRequestDTO;
import com.example.mariadb_project.models.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User createUser(UserRequestDTO user);
    User updateUser(UUID userId, UserUpdateRequestDTO updateRequest);
    User getUserById(UUID id);
    Optional<User> getUserByEmail(String email);
    List<User> getAllUsers();
    void deleteUser(UUID id);
    String login(UserRequestDTO loginRequest);
}
