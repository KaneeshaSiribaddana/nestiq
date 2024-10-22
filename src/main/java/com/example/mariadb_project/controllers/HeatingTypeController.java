package com.example.mariadb_project.controllers;
import com.example.mariadb_project.models.HeatingType;
import com.example.mariadb_project.services.HeatingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/heating-types")
public class HeatingTypeController {

    @Autowired
    private HeatingTypeService heatingTypeService;

    // Create HeatingType
    @PostMapping
    public ResponseEntity<HeatingType> createHeatingType(@RequestBody HeatingType heatingType) {
        HeatingType createdHeatingType = heatingTypeService.createHeatingType(heatingType);
        return ResponseEntity.status(201).body(createdHeatingType);
    }

    // Get all HeatingTypes
    @GetMapping
    public ResponseEntity<List<HeatingType>> getAllHeatingTypes() {
        List<HeatingType> heatingTypes = heatingTypeService.getAllHeatingTypes();
        return ResponseEntity.ok(heatingTypes);
    }

    // Get HeatingType by ID
    @GetMapping("/{id}")
    public ResponseEntity<HeatingType> getHeatingTypeById(@PathVariable String id) {
        HeatingType heatingType = heatingTypeService.getHeatingTypeById(id);
        return ResponseEntity.ok(heatingType);
    }

    // Update HeatingType
    @PutMapping("/{id}")
    public ResponseEntity<HeatingType> updateHeatingType(
            @PathVariable String id,
            @RequestBody HeatingType heatingType
    ) {
        HeatingType updatedHeatingType = heatingTypeService.updateHeatingType(id, heatingType);
        return ResponseEntity.ok(updatedHeatingType);
    }

    // Soft delete HeatingType
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHeatingType(@PathVariable String id) {
        heatingTypeService.deleteHeatingType(id);
        return ResponseEntity.ok().build();
    }
}

