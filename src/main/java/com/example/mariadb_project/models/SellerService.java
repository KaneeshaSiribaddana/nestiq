package com.example.mariadb_project.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "seller_services")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerService {
    @Id
    @GeneratedValue
    private UUID id; // UUID generated id

    private String name;
    private String percentage;
    private Boolean isMandatory;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Boolean isDeleted;

    // Getters and Setters
}
