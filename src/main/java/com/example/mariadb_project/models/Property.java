package com.example.mariadb_project.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "property_type", nullable = false)
    private String propertyType;

    @Column(name = "constructed_area", nullable = false)
    private double constructedArea;

    @Column(name = "rooms", nullable = false)
    private double rooms;

    @Column(name = "property_area", nullable = false)
    private double propertyArea;

    @Column(name = "construction_year", nullable = false)
    private int constructionYear;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "house_type", nullable = true)
    private String houseType;

    @Column(name = "publish_address", nullable = false)
    private boolean publishAddress;

    @Column(name = "usable_area", nullable = true)
    private Double usableArea;

    @Column(name = "historical_protection", nullable = false)
    private boolean historicalProtection;

    @Column(name = "floors", nullable = false)
    private int floors;

    @Column(name = "basement", nullable = false)
    private boolean basement;

    @Column(name = "quality", nullable = true)
    private String quality;

    @Column(name = "terrace", nullable = false)
    private boolean terrace;

    @Column(name = "balcony", nullable = false)
    private boolean balcony;

    @Column(name = "kitchen", nullable = false)
    private boolean kitchen;

    @Column(name = "garden", nullable = false)
    private boolean garden;

    @Column(name = "bathrooms", nullable = false)
    private double bathrooms;

    @Column(name = "free_at", nullable = true)
    private LocalDate freeAt;

    @Column(name = "property_state", nullable = true)
    private String propertyState;

    @Column(name = "energy_source", nullable = true)
    private String energySource;

    @Column(name = "modernisation_year", nullable = true)
    private Integer modernisationYear;

    @Column(name = "energy_report", nullable = true)
    private String energyReport;

    @Column(name = "heating_type", nullable = true)
    private String heatingType;

    @Column(name = "offer_type", nullable = true)
    private String offerType;

    @Column(name = "final_energy_value", nullable = false)
    private int finalEnergyValue;

    @Column(name = "energy_proof_available", nullable = true)
    private Boolean energyProofAvailable;

    @Column(name = "lift", nullable = true)
    private Boolean lift;

    @Column(name = "is_rented", nullable = true)
    private Boolean isRented;

    @Column(name = "parking_space", nullable = true)
    private String parkingSpace;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;

    // One property can have multiple owners
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    @JsonManagedReference // Indicates that this side owns the relationship
    private List<Owner> owners;

    // One property has one address
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id")
    private PropertyAddress address;


    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public PropertyAddress getPropertyAddress() {
        return address;
    }

    public List<Owner> getOwners() {
        return owners;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        modifiedAt = LocalDateTime.now();
    }
}
