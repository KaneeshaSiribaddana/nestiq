package com.example.mariadb_project.services;

import com.example.mariadb_project.models.PropertyType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PropertyTypeService {
    PropertyType createPropertyType(PropertyType propertyType);
    Optional<PropertyType> getPropertyTypeById(UUID id);
    List<PropertyType> getAllPropertyTypes();
    PropertyType updatePropertyType(UUID id, PropertyType propertyType);
    void deletePropertyType(UUID id);
}
