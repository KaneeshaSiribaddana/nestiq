package com.example.mariadb_project.controllers;

import com.example.mariadb_project.dtos.requests.PropertyDTO;
import com.example.mariadb_project.models.Property;
import com.example.mariadb_project.services.PropertyService;
import com.example.mariadb_project.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users/{userId}/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping
    public ResponseEntity<Response> createProperty(@PathVariable UUID userId, @RequestBody PropertyDTO propertyDTO) {
        Response response = propertyService.createProperty(userId, propertyDTO);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @PutMapping("/{propertyId}")
    public ResponseEntity<Response> updateProperty(
            @PathVariable UUID userId,
            @PathVariable UUID propertyId,
            @RequestBody PropertyDTO propertyDTO) {
        Response response = propertyService.updateProperty(userId, propertyId, propertyDTO);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @DeleteMapping("/{propertyId}")
    public ResponseEntity<Response> deleteProperty(
            @PathVariable UUID userId,
            @PathVariable UUID propertyId) {
        Response response = propertyService.softDeleteProperty(userId, propertyId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @GetMapping
    public ResponseEntity<Response> getPropertiesByUser(@PathVariable UUID userId) {
        Response response = propertyService.getPropertiesByUser(userId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}

