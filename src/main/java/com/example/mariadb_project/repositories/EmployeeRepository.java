package com.example.mariadb_project.repositories;

import com.example.mariadb_project.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
