package com.example.mariadb_project.services;

import com.example.mariadb_project.models.HouseType;

import java.util.List;

public interface HouseTypeService {

    List<HouseType> getAllHouseTypes();

    HouseType getHouseTypeById(String id);

    HouseType createHouseType(HouseType houseType);

    HouseType updateHouseType(String id, HouseType houseType);

    void deleteHouseType(String id);
}