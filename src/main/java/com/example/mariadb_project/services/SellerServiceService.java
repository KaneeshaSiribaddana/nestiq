package com.example.mariadb_project.services;

import com.example.mariadb_project.models.SellerService;

import java.util.List;
import java.util.UUID;

public interface SellerServiceService { // Renamed interface
    SellerService createSellerService(SellerService sellerService); // CRUD method
    List<SellerService> getAllSellerServices();
    SellerService getSellerServiceById(UUID id);
    SellerService updateSellerService(UUID id, SellerService sellerService);
    void softDeleteSellerService(UUID id); // Soft delete method
}