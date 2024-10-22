package com.example.mariadb_project.dtos.requests;

import com.example.mariadb_project.models.Owner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDTO {
    private String firstName;
    private String lastName;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;
    private String phone;
    private String email;
    private String mobile;

    public OwnerDTO(Owner owner) {
        this.firstName = owner.getFirstName();
        this.lastName = owner.getLastName();
        this.street = owner.getStreet();
        this.houseNumber = owner.getHouseNumber();
        this.postalCode = owner.getPostalCode();
        this.city = owner.getCity();
        this.phone = owner.getPhone();
        this.email = owner.getEmail();
        this.mobile = owner.getMobile();
    }
}
