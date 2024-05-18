package com.example.plantfinder.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.plantfinder.dto.PlantAddRequest;
import com.example.plantfinder.entity.Plant;
import com.example.plantfinder.repository.PlantRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlantService {
    private final PlantRepository plantRepository;

    public List<Plant> getAllPlants() {
        var s = plantRepository.findAll();
        System.out.println(s);
        return s;
    }

    public Optional<Plant> getPlantById(final String id) {
        final Optional<Plant> plant = plantRepository.findById(id);

        return plant;
    }

    public String savePlant(final PlantAddRequest plantAddRequest) {
        final Plant plant = new Plant(plantAddRequest.getName(), plantAddRequest.getImageUrl(),
                plantAddRequest.getLocation(), plantAddRequest.getLatitude(),
                plantAddRequest.getLongitude(), plantAddRequest.getDescription());
        plantRepository.save(plant);

        System.out.println(plant.getId());

        return plant.getId();
    }
}
