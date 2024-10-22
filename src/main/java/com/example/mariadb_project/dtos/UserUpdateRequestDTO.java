package com.example.mariadb_project.dtos;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequestDTO {

    private String firstname;
    private String lastname;

    private Integer gender;

    private String street;
    private String houseNumber;
    private String zipcode;
    private String city;

    private String displayname;
    private String company;

    private String sellerType;

    private String username;

    @Email(message = "Email should be valid")
    private String email;  // Optional email update (could be null)

}
