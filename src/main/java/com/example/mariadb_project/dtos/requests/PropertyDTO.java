package com.example.mariadb_project.dtos.requests;

import com.example.mariadb_project.models.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDTO {
    private String propertyType;
    private double constructedArea;
    private double rooms;
    private double propertyArea;
    private int constructionYear;
    private double price;
    private String houseType;
    private boolean publishAddress;
    private Double usableArea;
    private boolean historicalProtection;
    private int floors;
    private boolean basement;
    private String quality;
    private boolean terrace;
    private boolean balcony;
    private boolean kitchen;
    private boolean garden;
    private double bathrooms;
    private LocalDate freeAt;
    private String propertyState;
    private String energySource;
    private Integer modernisationYear;
    private String energyReport;
    private String heatingType;
    private String offerType;
    private int finalEnergyValue;
    private Boolean energyProofAvailable;
    private Boolean lift;
    private Boolean isRented;
    private String parkingSpace;
    private List<OwnerDTO> owners;
    private PropertyAddressDTO address;

    // Constructor that accepts a Property entity
    public PropertyDTO(Property property) {
        this.propertyType = property.getPropertyType();
        this.constructedArea = property.getConstructedArea();
        this.rooms = property.getRooms();
        this.propertyArea = property.getPropertyArea();
        this.constructionYear = property.getConstructionYear();
        this.price = property.getPrice();
        this.houseType = property.getHouseType();
        this.publishAddress = property.isPublishAddress();
        this.usableArea = property.getUsableArea();
        this.historicalProtection = property.isHistoricalProtection();
        this.floors = property.getFloors();
        this.basement = property.isBasement();
        this.quality = property.getQuality();
        this.terrace = property.isTerrace();
        this.balcony = property.isBalcony();
        this.kitchen = property.isKitchen();
        this.garden = property.isGarden();
        this.bathrooms = property.getBathrooms();
        this.freeAt = property.getFreeAt();
        this.propertyState = property.getPropertyState();
        this.energySource = property.getEnergySource();
        this.modernisationYear = property.getModernisationYear();
        this.energyReport = property.getEnergyReport();
        this.heatingType = property.getHeatingType();
        this.offerType = property.getOfferType();
        this.finalEnergyValue = property.getFinalEnergyValue();
        this.energyProofAvailable = property.getEnergyProofAvailable();
        this.lift = property.getLift();
        this.isRented = property.getIsRented();
        this.parkingSpace = property.getParkingSpace();

        // Convert Property owners to OwnerDTO list
        this.owners = property.getOwners()
                .stream()
                .map(OwnerDTO::new)
                .collect(Collectors.toList());

        // Convert Property address to PropertyAddressDTO
        this.address = new PropertyAddressDTO(property.getPropertyAddress());
    }
}
