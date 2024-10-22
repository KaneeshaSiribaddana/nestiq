package com.example.mariadb_project.repositories;
import com.example.mariadb_project.models.HouseType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface HouseTypeRepository extends JpaRepository<HouseType, String> {

    List<HouseType> findByIsDeletedFalse();

    Optional<HouseType> findByIdAndIsDeletedFalse(String id);
}
