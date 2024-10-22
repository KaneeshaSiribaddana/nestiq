package com.example.mariadb_project.repositories;

import com.example.mariadb_project.models.HeatingType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HeatingTypeRepository extends JpaRepository<HeatingType, String> {
    List<HeatingType> findAllByIsDeletedFalse();
}
