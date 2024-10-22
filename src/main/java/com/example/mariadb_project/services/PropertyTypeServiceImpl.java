package com.example.mariadb_project.services.impl;

import com.example.mariadb_project.models.PropertyState;
import com.example.mariadb_project.models.PropertyType;
import com.example.mariadb_project.repositories.PropertyTypeRepository;
import com.example.mariadb_project.services.PropertyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PropertyTypeServiceImpl implements PropertyTypeService {

    @Autowired
    private PropertyTypeRepository propertyTypeRepository;

    @Override
    public PropertyType createPropertyType(PropertyType propertyType) {
        propertyType.setCreatedAt(LocalDateTime.now());
        return propertyTypeRepository.save(propertyType);
    }

    @Override
    public Optional<PropertyType> getPropertyTypeById(UUID id) {
        return propertyTypeRepository.findById(id);
    }

    @Override
    public List<PropertyType> getAllPropertyTypes() {
        return propertyTypeRepository.findAll();
    }

    @Override
    public PropertyType updatePropertyType(UUID id, PropertyType propertyType) {
        Optional<PropertyType> existingPropertyType = propertyTypeRepository.findById(id);

        if (existingPropertyType.isPresent()) {
            PropertyType updatedPropertyType = existingPropertyType.get();
            updatedPropertyType.setName(propertyType.getName());
            updatedPropertyType.setModifiedAt(LocalDateTime.now());
            updatedPropertyType.setIs24(propertyType.getIs24());
            return propertyTypeRepository.save(updatedPropertyType);
        } else {
            throw new RuntimeException("PropertyType not found");
        }
    }

    @Override
    public void deletePropertyType(UUID id) {
        PropertyType propertyType = propertyTypeRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("PropertyType not found"));

        propertyType.setDeleted(true);
        propertyTypeRepository.save(propertyType);
    }
}

