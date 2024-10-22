package com.example.mariadb_project.services;

import com.example.mariadb_project.models.PropertyState;

import java.util.List;
import java.util.UUID;

public interface PropertyStateService {
    List<PropertyState> getAllPropertyStates();
    PropertyState getPropertyStateById(UUID id);
    PropertyState createPropertyState(PropertyState propertyState);
    PropertyState updatePropertyState(UUID id, PropertyState propertyState);
    void softDeletePropertyState(UUID id);
}
