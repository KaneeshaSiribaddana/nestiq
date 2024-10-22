package com.example.mariadb_project.services;

import com.example.mariadb_project.dtos.UserRequestDTO;
import com.example.mariadb_project.dtos.UserUpdateRequestDTO;
import com.example.mariadb_project.models.User;
import com.example.mariadb_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public User createUser(UserRequestDTO userRequestDTO) {
        // Check if the email already exists
        Optional<User> existingUser = userRepository.findByEmail(userRequestDTO.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Email is already in use.");
        }

        // Map DTO to User entity
        User user = new User();
        user.setEmail(userRequestDTO.getEmail());

        String encodedPassword = passwordEncoder.encode(userRequestDTO.getPassword());
        user.setPassword(encodedPassword);

        user.setCreatedAt(LocalDateTime.now());
        user.setModifiedAt(LocalDateTime.now());

        // Save the new user
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public User updateUser(UUID userId, UserUpdateRequestDTO updateRequest) {
        // Fetch the existing user
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Check if the email needs to be updated and if it's already in use
        if (updateRequest.getEmail() != null && !updateRequest.getEmail().equals(existingUser.getEmail())) {
            if (userRepository.findByEmail(updateRequest.getEmail()).isPresent()) {
                throw new IllegalArgumentException("Email is already in use.");
            }
            existingUser.setEmail(updateRequest.getEmail());
        }

        // Apply only the provided fields from updateRequest
        if (updateRequest.getFirstname() != null) {
            existingUser.setFirstname(updateRequest.getFirstname());
        }
        if (updateRequest.getLastname() != null) {
            existingUser.setLastname(updateRequest.getLastname());
        }
        if (updateRequest.getGender() != null) {
            existingUser.setGender(updateRequest.getGender());
        }
        if (updateRequest.getStreet() != null) {
            existingUser.setStreet(updateRequest.getStreet());
        }
        if (updateRequest.getHouseNumber() != null) {
            existingUser.setHouseNumber(updateRequest.getHouseNumber());
        }
        if (updateRequest.getZipcode() != null) {
            existingUser.setZipcode(updateRequest.getZipcode());
        }
        if (updateRequest.getCity() != null) {
            existingUser.setCity(updateRequest.getCity());
        }
        if (updateRequest.getDisplayname() != null) {
            existingUser.setDisplayname(updateRequest.getDisplayname());
        }
        if (updateRequest.getCompany() != null) {
            existingUser.setCompany(updateRequest.getCompany());
        }
        if (updateRequest.getSellerType() != null) {
            existingUser.setSellerType(updateRequest.getSellerType());
        }
        if (updateRequest.getUsername() != null) {
            existingUser.setUsername(updateRequest.getUsername());
        }

        // Update the modifiedAt field
        existingUser.setModifiedAt(java.time.LocalDateTime.now());

        // Save and return the updated user
        return userRepository.save(existingUser);
    }


    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.isDeleted()) {
            user.setDeleted(true);
            user.setDeletedAt(LocalDateTime.now());
            userRepository.save(user);
        } else {
            throw new RuntimeException("User is already deleted");
        }
    }


    @Override
    public String login(UserRequestDTO loginRequest) {
        // Fetch the user by email
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        // Check if the password is correct
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            if (user.isDeleted()) {
                throw new RuntimeException("User account is deactivated");
            }
            // You can return a JWT token here instead of a string if you're using JWT for authentication
            return "Login successful!";
        } else {
            throw new RuntimeException("Invalid email or password");
        }
    }
}
