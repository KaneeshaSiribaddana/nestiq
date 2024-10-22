package com.example.mariadb_project.controllers;

import com.example.mariadb_project.models.SellerService;
import com.example.mariadb_project.services.SellerServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/seller-services")
public class SellerServiceController {

    @Autowired
    private SellerServiceService sellerServiceService;

    @PostMapping
    public ResponseEntity<SellerService> createSellerService(@RequestBody SellerService sellerService) {
        SellerService createdService = sellerServiceService.createSellerService(sellerService);
        return ResponseEntity.ok(createdService);
    }

    @GetMapping
    public ResponseEntity<List<SellerService>> getAllSellerServices() {
        List<SellerService> services = sellerServiceService.getAllSellerServices();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellerService> getSellerServiceById(@PathVariable UUID id) {
        SellerService service = sellerServiceService.getSellerServiceById(id);
        return ResponseEntity.ok(service);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SellerService> updateSellerService(@PathVariable UUID id, @RequestBody SellerService sellerService) {
        SellerService updatedService = sellerServiceService.updateSellerService(id, sellerService);
        return ResponseEntity.ok(updatedService);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> softDeleteSellerService(@PathVariable UUID id) {
        sellerServiceService.softDeleteSellerService(id);
        return ResponseEntity.ok("Seller service deleted successfully.");
    }

}
