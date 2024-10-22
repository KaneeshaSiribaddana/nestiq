package com.example.mariadb_project.controllers;

import com.example.mariadb_project.models.PropertyState;
import com.example.mariadb_project.services.PropertyStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/property-states")
public class PropertyStateController {

    @Autowired
    private PropertyStateService propertyStateService;

    // Get all PropertyStates (excluding soft-deleted ones)
    @GetMapping
    public ResponseEntity<List<PropertyState>> getAllPropertyStates() {
        List<PropertyState> propertyStates = propertyStateService.getAllPropertyStates();
        return ResponseEntity.ok(propertyStates);
    }

    // Get PropertyState by ID (excluding soft-deleted ones)
    @GetMapping("/{id}")
    public ResponseEntity<PropertyState> getPropertyStateById(@PathVariable UUID id) {
        PropertyState propertyState = propertyStateService.getPropertyStateById(id);
        return ResponseEntity.ok(propertyState);
    }

    // Create PropertyState
    @PostMapping
    public ResponseEntity<PropertyState> createPropertyState(@RequestBody PropertyState propertyState) {
        PropertyState createdPropertyState = propertyStateService.createPropertyState(propertyState);
        return ResponseEntity.ok(createdPropertyState);
    }

    // Update PropertyState
    @PutMapping("/{id}")
    public ResponseEntity<PropertyState> updatePropertyState(@PathVariable UUID id, @RequestBody PropertyState propertyState) {
        PropertyState updatedPropertyState = propertyStateService.updatePropertyState(id, propertyState);
        return ResponseEntity.ok(updatedPropertyState);
    }

    // Soft delete PropertyState
    @DeleteMapping("/{id}")
    public ResponseEntity<String> softDeletePropertyState(@PathVariable UUID id) {
        propertyStateService.softDeletePropertyState(id);
        return ResponseEntity.ok("PropertyState soft deleted successfully.");
    }
}
