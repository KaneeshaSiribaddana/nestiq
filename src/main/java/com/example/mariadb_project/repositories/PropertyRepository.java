package com.example.mariadb_project.repositories;

import com.example.mariadb_project.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PropertyRepository extends JpaRepository<Property, UUID> {

    // Custom query method to find all properties by user ID
    List<Property> findByUserId(UUID userId);

    // Custom query method to find properties by user and exclude soft deleted properties
    List<Property> findByUserIdAndIsDeletedFalse(UUID userId);
}
