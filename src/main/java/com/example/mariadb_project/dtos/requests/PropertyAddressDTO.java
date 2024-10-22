package com.example.mariadb_project.dtos.requests;

import com.example.mariadb_project.models.PropertyAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyAddressDTO {
    private String street;
    private String houseNumber;
    private String zipCode;
    private String location;

    public PropertyAddressDTO(PropertyAddress propertyAddress) {
        this.street = propertyAddress.getStreet();
        this.houseNumber = propertyAddress.getHouseNumber();
        this.zipCode = propertyAddress.getZipCode();
        this.location = propertyAddress.getLocation();
    }
}
