package com.example.mariadb_project.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "CHAR(36)")
    private UUID id;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    // Optional fields during creation
    private String firstname;

    private String lastname;

    private Integer gender;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;

    private LocalDateTime deletedAt;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "phone", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "phone")
    private List<String> phones;

    @ElementCollection
    @CollectionTable(name = "address", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "address")
    private List<String> addresses;

    @ElementCollection
    @CollectionTable(name = "offer", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "offer")
    private List<String> offers;

    @ElementCollection
    @CollectionTable(name = "notification", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "notification")
    private List<String> notifications;

    private boolean verified;

    private String street;

    private String houseNumber;

    private String zipcode;

    private String city;

    private String sellerType;

    private String displayname;

    private String company;

    @Column(nullable = true)
    private String username;

    @Column(nullable = true, unique = true)
    private String userIdentifier;

    private boolean completed;

    private boolean isDeleted;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        modifiedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        modifiedAt = LocalDateTime.now();
    }
}
