package com.example.mariadb_project.controllers;
import com.example.mariadb_project.models.SellerType;
import com.example.mariadb_project.repositories.SellerTypeRepository;
import com.example.mariadb_project.services.SellerTypeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/seller-types")
public class SellerTypeController {

    @Autowired
    private SellerTypeService sellerTypeService;

    @Autowired
    private SellerTypeRepository sellerTypeRepository;

    // Get all SellerTypes
    @GetMapping
    public ResponseEntity<List<SellerType>> getAllSellerTypes() {
        List<SellerType> sellerTypes = sellerTypeService.getAllSellerTypes();
        return ResponseEntity.ok(sellerTypes);
    }


    // Create SellerType
    @PostMapping
    public ResponseEntity<SellerType> createSellerType(@RequestBody SellerType sellerType) {
        SellerType createdSellerType = sellerTypeService.createSellerType(sellerType);
        return ResponseEntity.ok(createdSellerType);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellerType> getSellerTypeById(@PathVariable UUID id) {
        SellerType sellerType = sellerTypeService.getSellerTypeById(id);
        return ResponseEntity.ok(sellerType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SellerType> updateSellerType(@PathVariable UUID id, @RequestBody SellerType sellerType) {
        SellerType updatedSellerType = sellerTypeService.updateSellerType(id, sellerType);
        return ResponseEntity.ok(updatedSellerType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> softDeleteSellerType(@PathVariable UUID id) {
        SellerType sellerType = sellerTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SellerType not found"));

        if (sellerType.isDeleted()) {
            return ResponseEntity.ok("SellerType is already soft deleted");
        }

        sellerTypeService.softDeleteSellerType(id);
        return ResponseEntity.ok("SellerType soft deleted successfully");
    }

}
