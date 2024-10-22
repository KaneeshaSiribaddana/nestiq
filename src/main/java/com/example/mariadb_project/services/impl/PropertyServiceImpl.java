package com.example.mariadb_project.services.impl;

import com.example.mariadb_project.dtos.requests.PropertyDTO;
import com.example.mariadb_project.models.Owner;
import com.example.mariadb_project.models.Property;
import com.example.mariadb_project.models.PropertyAddress;
import com.example.mariadb_project.repositories.PropertyRepository;
import com.example.mariadb_project.repositories.UserRepository;
import com.example.mariadb_project.services.PropertyService;
import com.example.mariadb_project.models.User;
import com.example.mariadb_project.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Response createProperty(UUID userId, PropertyDTO propertyDTO) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Property property = new Property();

            // Set user to property
            property.setUser(user);

            // Set other fields from propertyDTO
            property.setPropertyType(propertyDTO.getPropertyType());
            property.setConstructedArea(propertyDTO.getConstructedArea());
            property.setRooms(propertyDTO.getRooms());
            property.setPropertyArea(propertyDTO.getPropertyArea());
            property.setPrice(propertyDTO.getPrice());
            property.setHouseType(propertyDTO.getHouseType());
            property.setPublishAddress(propertyDTO.isPublishAddress());
            property.setUsableArea(propertyDTO.getUsableArea());
            property.setHistoricalProtection(propertyDTO.isHistoricalProtection());
            property.setFloors(propertyDTO.getFloors());
            property.setBasement(propertyDTO.isBasement());
            property.setQuality(propertyDTO.getQuality());
            property.setTerrace(propertyDTO.isTerrace());
            property.setBalcony(propertyDTO.isBalcony());
            property.setKitchen(propertyDTO.isKitchen());
            property.setGarden(propertyDTO.isGarden());
            property.setBathrooms(propertyDTO.getBathrooms());
            property.setFreeAt(propertyDTO.getFreeAt());
            property.setPropertyState(propertyDTO.getPropertyState());
            property.setEnergySource(propertyDTO.getEnergySource());
            property.setModernisationYear(propertyDTO.getModernisationYear());
            property.setEnergyReport(propertyDTO.getEnergyReport());
            property.setHeatingType(propertyDTO.getHeatingType());
            property.setOfferType(propertyDTO.getOfferType());
            property.setFinalEnergyValue(propertyDTO.getFinalEnergyValue());
            property.setEnergyProofAvailable(propertyDTO.getEnergyProofAvailable());
            property.setLift(propertyDTO.getLift());
            property.setIsRented(propertyDTO.getIsRented());
            property.setParkingSpace(propertyDTO.getParkingSpace());

            // Set Owners and associate them with the property
            List<Owner> owners = propertyDTO.getOwners().stream().map(ownerDTO -> {
                Owner owner = new Owner();
                owner.setFirstName(ownerDTO.getFirstName());
                owner.setLastName(ownerDTO.getLastName());
                owner.setStreet(ownerDTO.getStreet());
                owner.setHouseNumber(ownerDTO.getHouseNumber());
                owner.setPostalCode(ownerDTO.getPostalCode());
                owner.setCity(ownerDTO.getCity());
                owner.setPhone(ownerDTO.getPhone());
                owner.setEmail(ownerDTO.getEmail());
                owner.setMobile(ownerDTO.getMobile());

                // Link owner to the property
                owner.setProperty(property);
                return owner;
            }).collect(Collectors.toList());
            property.setOwners(owners); // Set owners to property

            // Set Address and associate it with the property
            PropertyAddress address = new PropertyAddress();
            address.setStreet(propertyDTO.getAddress().getStreet());
            address.setHouseNumber(propertyDTO.getAddress().getHouseNumber());
            address.setZipCode(propertyDTO.getAddress().getZipCode());
            address.setLocation(propertyDTO.getAddress().getLocation());

            // Link address to the property
            address.setProperty(property);
            property.setAddress(address);

            // Save the property, which will cascade the save to owners and address
            Property createdProperty = propertyRepository.save(property);

            // Return success response with the created property data
            return new Response(HttpStatus.CREATED.value(), "Property created successfully", createdProperty);
        }

        // Return error response if user is not found
        return new Response(HttpStatus.NOT_FOUND.value(), "User not found", null);
    }



    @Override
    public Response updateProperty(UUID userId, UUID propertyId, PropertyDTO propertyDTO) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            Optional<Property> optionalProperty = propertyRepository.findById(propertyId);
            if (optionalProperty.isPresent()) {
                Property property = optionalProperty.get();

                // Ensure the property belongs to the user
                if (!property.getUser().getId().equals(userId)) {
                    return new Response(HttpStatus.FORBIDDEN.value(), "Property does not belong to the user",null);
                }

                // Update property fields
                property.setPropertyType(propertyDTO.getPropertyType());
                property.setConstructedArea(propertyDTO.getConstructedArea());
                property.setRooms(propertyDTO.getRooms());
                property.setPropertyArea(propertyDTO.getPropertyArea());
                property.setConstructionYear(propertyDTO.getConstructionYear());
                property.setPrice(propertyDTO.getPrice());
                property.setHouseType(propertyDTO.getHouseType());
                property.setPublishAddress(propertyDTO.isPublishAddress());
                property.setUsableArea(propertyDTO.getUsableArea());
                property.setHistoricalProtection(propertyDTO.isHistoricalProtection());
                property.setFloors(propertyDTO.getFloors());
                property.setBasement(propertyDTO.isBasement());
                property.setQuality(propertyDTO.getQuality());
                property.setTerrace(propertyDTO.isTerrace());
                property.setBalcony(propertyDTO.isBalcony());
                property.setKitchen(propertyDTO.isKitchen());
                property.setGarden(propertyDTO.isGarden());
                property.setBathrooms(propertyDTO.getBathrooms());
                property.setFreeAt(propertyDTO.getFreeAt());
                property.setPropertyState(propertyDTO.getPropertyState());
                property.setEnergySource(propertyDTO.getEnergySource());
                property.setModernisationYear(propertyDTO.getModernisationYear());
                property.setEnergyReport(propertyDTO.getEnergyReport());
                property.setHeatingType(propertyDTO.getHeatingType());
                property.setOfferType(propertyDTO.getOfferType());
                property.setFinalEnergyValue(propertyDTO.getFinalEnergyValue());
                property.setEnergyProofAvailable(propertyDTO.getEnergyProofAvailable());
                property.setLift(propertyDTO.getLift());
                property.setIsRented(propertyDTO.getIsRented());
                property.setParkingSpace(propertyDTO.getParkingSpace());

                // Update Owners
                List<Owner> owners = propertyDTO.getOwners().stream().map(ownerDTO -> {
                    Owner owner = new Owner();
                    owner.setFirstName(ownerDTO.getFirstName());
                    owner.setLastName(ownerDTO.getLastName());
                    owner.setStreet(ownerDTO.getStreet());
                    owner.setHouseNumber(ownerDTO.getHouseNumber());
                    owner.setPostalCode(ownerDTO.getPostalCode());
                    owner.setCity(ownerDTO.getCity());
                    owner.setPhone(ownerDTO.getPhone());
                    owner.setEmail(ownerDTO.getEmail());
                    owner.setMobile(ownerDTO.getMobile());
                    return owner;
                }).collect(Collectors.toList());
                property.setOwners(owners);

                // Update Address
                PropertyAddress address = new PropertyAddress();
                address.setStreet(propertyDTO.getAddress().getStreet());
                address.setHouseNumber(propertyDTO.getAddress().getHouseNumber());
                address.setZipCode(propertyDTO.getAddress().getZipCode());
                address.setLocation(propertyDTO.getAddress().getLocation());
                property.setAddress(address);

                Property updatedProperty = propertyRepository.save(property);
                return new Response(HttpStatus.OK.value(), "Property updated successfully",updatedProperty);
            }
            return new Response(HttpStatus.NOT_FOUND.value(), "Property not found",null);
        }
        return new Response(HttpStatus.NOT_FOUND.value(), "User not found",null);
    }

    @Override
    public Response softDeleteProperty(UUID userId, UUID propertyId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            Optional<Property> optionalProperty = propertyRepository.findById(propertyId);
            if (optionalProperty.isPresent()) {
                Property property = optionalProperty.get();

                // Ensure the property belongs to the user
                if (!property.getUser().getId().equals(userId)) {
                    return new Response(HttpStatus.FORBIDDEN.value(), "Property does not belong to the user",null);
                }

                // Perform soft delete
                property.setDeleted(true);
                propertyRepository.save(property);

                return new Response(HttpStatus.OK.value(), "Property deleted successfully",null);
            }
            return new Response(HttpStatus.NOT_FOUND.value(), "Property not found",null);
        }
        return new Response(HttpStatus.NOT_FOUND.value(), "User not found",null);
    }

    @Override
    public Response getPropertiesByUser(UUID userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            List<Property> properties = propertyRepository.findByUserId(userId);
            List<PropertyDTO> propertyDTOs = properties.stream()
                    .map(property -> new PropertyDTO(property))
                    .collect(Collectors.toList());
            return new Response(HttpStatus.OK.value(),"Properties retrieved successfully", propertyDTOs);
        }
        return new Response(HttpStatus.NOT_FOUND.value(), "User not found",null);
    }
}
