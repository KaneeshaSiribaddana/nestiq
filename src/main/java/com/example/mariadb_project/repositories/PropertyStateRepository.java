package com.example.mariadb_project.repositories;

import com.example.mariadb_project.models.PropertyState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PropertyStateRepository extends JpaRepository<PropertyState, UUID> {
    List<PropertyState> findAllByIsDeletedFalse();
    Optional<PropertyState> findByIdAndIsDeletedFalse(UUID id);
}
