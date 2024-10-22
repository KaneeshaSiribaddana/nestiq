package com.example.mariadb_project.repositories;

import com.example.mariadb_project.models.PropertyState;
import com.example.mariadb_project.models.PropertyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PropertyTypeRepository extends JpaRepository<PropertyType, UUID> {
    List<PropertyType> findAllByIsDeletedFalse();
    Optional<PropertyType> findByIdAndIsDeletedFalse(UUID id);
}
