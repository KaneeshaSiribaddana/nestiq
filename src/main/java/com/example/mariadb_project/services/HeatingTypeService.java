package com.example.mariadb_project.services;

import com.example.mariadb_project.models.HeatingType;

import java.util.List;

public interface HeatingTypeService {
    HeatingType createHeatingType(HeatingType heatingType);
    HeatingType updateHeatingType(String id, HeatingType heatingType);
    HeatingType getHeatingTypeById(String id);
    void deleteHeatingType(String id);
    List<HeatingType> getAllHeatingTypes();
}
