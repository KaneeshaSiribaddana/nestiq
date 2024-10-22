package com.example.mariadb_project.services;

import com.example.mariadb_project.models.HouseType;
import com.example.mariadb_project.repositories.HouseTypeRepository;
import com.example.mariadb_project.services.HouseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseTypeServiceImpl implements HouseTypeService {

    @Autowired
    private HouseTypeRepository houseTypeRepository;

    @Override
    public List<HouseType> getAllHouseTypes() {
        return houseTypeRepository.findByIsDeletedFalse();
    }

    @Override
    public HouseType getHouseTypeById(String id) {
        return houseTypeRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("HouseType not found with id: " + id));
    }

    @Override
    public HouseType createHouseType(HouseType houseType) {
        houseType.setDeleted(false);
        return houseTypeRepository.save(houseType);
    }

    @Override
    public HouseType updateHouseType(String id, HouseType houseTypeDetails) {
        HouseType houseType = getHouseTypeById(id); // Fetching existing house type
        houseType.setName(houseTypeDetails.getName());
        houseType.setIs24(houseTypeDetails.getIs24());
        houseType.setSort(houseTypeDetails.getSort());
        return houseTypeRepository.save(houseType); // Saving updated house type
    }

    @Override
    public void deleteHouseType(String id) {
        HouseType houseType = getHouseTypeById(id); // Fetching the entity before marking as deleted
        houseType.setDeleted(true);
        houseTypeRepository.save(houseType); // Updating the record
    }
}
