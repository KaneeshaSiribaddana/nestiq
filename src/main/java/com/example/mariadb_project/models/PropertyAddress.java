package com.example.mariadb_project.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "property_address")
public class PropertyAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "address", cascade = CascadeType.ALL)
    @JsonBackReference
    private Property property;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "house_number", nullable = false)
    private String houseNumber;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @Column(name = "location", nullable = false)
    private String location;
}
