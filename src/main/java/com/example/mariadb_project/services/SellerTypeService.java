package com.example.mariadb_project.services;

import com.example.mariadb_project.models.SellerType;

import java.util.List;
import java.util.UUID;

public interface SellerTypeService {
    List<SellerType> getAllSellerTypes();
    SellerType getSellerTypeById(UUID id);
    SellerType createSellerType(SellerType sellerType);
    SellerType updateSellerType(UUID id, SellerType sellerType);
    void softDeleteSellerType(UUID id);
}