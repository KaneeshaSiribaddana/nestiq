package com.example.mariadb_project.services;

import com.example.mariadb_project.models.PropertyState;
import com.example.mariadb_project.repositories.PropertyStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PropertyStateServiceImpl implements PropertyStateService {

    @Autowired
    private PropertyStateRepository propertyStateRepository;

    @Override
    public List<PropertyState> getAllPropertyStates() {
        // Fetch all records where isDeleted is false
        return propertyStateRepository.findAllByIsDeletedFalse();
    }

    @Override
    public PropertyState getPropertyStateById(UUID id) {
        return propertyStateRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("PropertyState not found"));
    }

    @Override
    public PropertyState createPropertyState(PropertyState propertyState) {
        return propertyStateRepository.save(propertyState);
    }

    @Override
    public PropertyState updatePropertyState(UUID id, PropertyState propertyState) {
        PropertyState existingPropertyState = propertyStateRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("PropertyState not found"));

        existingPropertyState.setName(propertyState.getName());
        existingPropertyState.setIs24(propertyState.getIs24());
        existingPropertyState.setSort(propertyState.getSort());
        existingPropertyState.setModifiedAt(propertyState.getModifiedAt());

        return propertyStateRepository.save(existingPropertyState);
    }

    @Override
    public void softDeletePropertyState(UUID id) {
        PropertyState propertyState = propertyStateRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("PropertyState not found"));

        propertyState.setDeleted(true);  // Set isDeleted to true for soft delete
        propertyStateRepository.save(propertyState);
    }
}
