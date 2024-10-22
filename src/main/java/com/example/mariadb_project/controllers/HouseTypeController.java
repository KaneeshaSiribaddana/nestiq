package com.example.mariadb_project.controllers;

import com.example.mariadb_project.models.HouseType;
import com.example.mariadb_project.services.HouseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.example.mariadb_project.models.Property;
import com.example.mariadb_project.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/house-types")
public class HouseTypeController {

    @Autowired
    private HouseTypeService houseTypeService;

    @GetMapping
    public ResponseEntity<List<HouseType>> getAllHouseTypes() {
        List<HouseType> houseTypes = houseTypeService.getAllHouseTypes();
        return new ResponseEntity<>(houseTypes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HouseType> getHouseTypeById(@PathVariable String id) {
        HouseType houseType = houseTypeService.getHouseTypeById(id);
        return new ResponseEntity<>(houseType, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HouseType> createHouseType(@RequestBody HouseType houseType) {
        HouseType createdHouseType = houseTypeService.createHouseType(houseType);
        return new ResponseEntity<>(createdHouseType, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HouseType> updateHouseType(@PathVariable String id, @RequestBody HouseType houseType) {
        HouseType updatedHouseType = houseTypeService.updateHouseType(id, houseType);
        return new ResponseEntity<>(updatedHouseType, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHouseType(@PathVariable String id) {
        houseTypeService.deleteHouseType(id);
        return new ResponseEntity<>("HouseType soft deleted successfully", HttpStatus.OK);
    }
}
