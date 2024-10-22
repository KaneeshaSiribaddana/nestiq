package com.example.mariadb_project.services;
import com.example.mariadb_project.models.HeatingType;
import com.example.mariadb_project.repositories.HeatingTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HeatingTypeServiceImpl implements HeatingTypeService {

    @Autowired
    private HeatingTypeRepository heatingTypeRepository;

    @Override
    public HeatingType createHeatingType(HeatingType heatingType) {
        return heatingTypeRepository.save(heatingType);
    }

    @Override
    public HeatingType updateHeatingType(String id, HeatingType heatingType) {
        HeatingType existingHeatingType = getHeatingTypeById(id);
        existingHeatingType.setName(heatingType.getName());
        existingHeatingType.setIs24(heatingType.getIs24());
        existingHeatingType.setSort(heatingType.getSort());
        return heatingTypeRepository.save(existingHeatingType);
    }

    @Override
    public HeatingType getHeatingTypeById(String id) {
        return heatingTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HeatingType not found with id: " + id));
    }

    @Override
    public void deleteHeatingType(String id) {
        HeatingType heatingType = getHeatingTypeById(id);
        heatingType.setDeleted(true);
        heatingTypeRepository.save(heatingType);
    }

    @Override
    public List<HeatingType> getAllHeatingTypes() {
        return heatingTypeRepository.findAllByIsDeletedFalse();  // Fetch only non-deleted entries
    }
}
