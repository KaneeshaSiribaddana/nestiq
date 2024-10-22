package com.example.mariadb_project.services;

import com.example.mariadb_project.dtos.requests.PropertyDTO;
import com.example.mariadb_project.models.Property;
import com.example.mariadb_project.utils.Response;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PropertyService {
    Response createProperty(UUID userId, PropertyDTO propertyDTO);
    Response updateProperty(UUID userId, UUID propertyId, PropertyDTO propertyDTO);
    Response softDeleteProperty(UUID userId, UUID propertyId);
    Response getPropertiesByUser(UUID userId);
}

