package com.example.mariadb_project.services;
import com.example.mariadb_project.models.SellerService;
import com.example.mariadb_project.repositories.SellerServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SellerServiceServiceImpl implements SellerServiceService { // Renamed implementation class

    @Autowired
    private SellerServiceRepository sellerServiceRepository;

    @Override
    public SellerService createSellerService(SellerService sellerService) {
        sellerService.setCreatedAt(LocalDateTime.now());
        sellerService.setModifiedAt(LocalDateTime.now());
        sellerService.setIsDeleted(false);
        return sellerServiceRepository.save(sellerService);
    }

    @Override
    public List<SellerService> getAllSellerServices() {
        return sellerServiceRepository.findAll().stream()
                .filter(sellerService -> !sellerService.getIsDeleted())
                .toList();
    }

    @Override
    public SellerService getSellerServiceById(UUID id) {
        Optional<SellerService> sellerService = sellerServiceRepository.findById(id);
        if (sellerService.isPresent() && !sellerService.get().getIsDeleted()) {
            return sellerService.get();
        }
        throw new RuntimeException("Service not found");
    }

    @Override
    public SellerService updateSellerService(UUID id, SellerService sellerServiceDetails) {
        SellerService sellerService = sellerServiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        if (sellerService.getIsDeleted()) {
            throw new RuntimeException("Cannot update a deleted service");
        }

        sellerService.setName(sellerServiceDetails.getName());
        sellerService.setPercentage(sellerServiceDetails.getPercentage());
        sellerService.setIsMandatory(sellerServiceDetails.getIsMandatory());
        sellerService.setModifiedAt(LocalDateTime.now());

        return sellerServiceRepository.save(sellerService);
    }

    @Override
    public void softDeleteSellerService(UUID id) {
        SellerService sellerService = sellerServiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        sellerService.setIsDeleted(true);
        sellerService.setModifiedAt(LocalDateTime.now());
        sellerServiceRepository.save(sellerService);
    }
}
