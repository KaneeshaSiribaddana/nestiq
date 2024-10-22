package com.example.mariadb_project.services;

import com.example.mariadb_project.models.SellerType;
import com.example.mariadb_project.repositories.SellerTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SellerTypeServiceImpl implements SellerTypeService {

    @Autowired
    private SellerTypeRepository sellerTypeRepository;

    @Override
    public List<SellerType> getAllSellerTypes() {
        return sellerTypeRepository.findAllByIsDeletedFalse();
    }


    @Override
    public SellerType createSellerType(SellerType sellerType) {
        return sellerTypeRepository.save(sellerType);
    }

    @Override
    public SellerType getSellerTypeById(UUID id) {
        return sellerTypeRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("SellerType not found"));
    }

    @Override
    public SellerType updateSellerType(UUID id, SellerType updatedSellerType) {
        SellerType existingSellerType = sellerTypeRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("SellerType not found"));

        existingSellerType.setName(updatedSellerType.getName());
        existingSellerType.setSort(updatedSellerType.getSort());
        existingSellerType.setModifiedAt(LocalDateTime.now());

        return sellerTypeRepository.save(existingSellerType);
    }

    @Override
    public void softDeleteSellerType(UUID id) {
        SellerType sellerType = sellerTypeRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("SellerType not found"));
        sellerType.setDeleted(true);
        sellerTypeRepository.save(sellerType);
    }

}
