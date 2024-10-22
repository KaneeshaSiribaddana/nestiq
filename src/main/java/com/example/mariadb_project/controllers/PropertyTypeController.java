package com.example.mariadb_project.controllers;

import com.example.mariadb_project.models.PropertyType;
import com.example.mariadb_project.services.PropertyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/property-types")
public class PropertyTypeController {

    @Autowired
    private PropertyTypeService propertyTypeService;

    @PostMapping
    public ResponseEntity<PropertyType> createPropertyType(@RequestBody PropertyType propertyType) {
        PropertyType createdPropertyType = propertyTypeService.createPropertyType(propertyType);
        return new ResponseEntity<>(createdPropertyType, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPropertyTypeById(@PathVariable UUID id) {
        Optional<PropertyType> propertyType = propertyTypeService.getPropertyTypeById(id);
        if (propertyType.isPresent()) {
            return new ResponseEntity<>(propertyType.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("PropertyType not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<PropertyType>> getAllPropertyTypes() {
        List<PropertyType> propertyTypes = propertyTypeService.getAllPropertyTypes();
        return new ResponseEntity<>(propertyTypes, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePropertyType(@PathVariable UUID id, @RequestBody PropertyType propertyType) {
        try {
            PropertyType updatedPropertyType = propertyTypeService.updatePropertyType(id, propertyType);
            return new ResponseEntity<>(updatedPropertyType, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePropertyType(@PathVariable UUID id) {
        try {
            propertyTypeService.deletePropertyType(id);
            return new ResponseEntity<>("PropertyType deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete PropertyType", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
