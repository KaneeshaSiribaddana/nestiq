package com.example.mariadb_project.repositories;

import com.example.mariadb_project.models.SellerService;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SellerServiceRepository extends JpaRepository<SellerService, UUID> {
    // Additional query methods can be added here if necessary
}