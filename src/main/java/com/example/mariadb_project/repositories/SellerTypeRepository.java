package com.example.mariadb_project.repositories;

import com.example.mariadb_project.models.SellerType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID; // Import UUID

public interface SellerTypeRepository extends JpaRepository<SellerType, UUID> {
    List<SellerType> findAllByIsDeletedFalse();
    Optional<SellerType> findByIdAndIsDeletedFalse(UUID id);
}
