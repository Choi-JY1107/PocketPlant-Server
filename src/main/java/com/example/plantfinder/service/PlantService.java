package com.example.plantfinder.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.plantfinder.dto.IsPlantResponse;
import com.example.plantfinder.dto.PlantAddRequest;
import com.example.plantfinder.dto.PlantResponse;
import com.example.plantfinder.entity.Plant;
import com.example.plantfinder.repository.PlantRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlantService {
    private final PlantRepository plantRepository;

    public List<PlantResponse> getAllPlants() {
        return plantRepository.findAll().stream().map(PlantResponse::new).toList();
    }

    public PlantResponse getPlantById(final String id) {
        final var plant = plantRepository.findById(id).orElseThrow(
                () -> new RuntimeException("식물이 없어요")
        );

        return new PlantResponse(plant);
    }

    public String savePlant(final PlantAddRequest plantAddRequest) {
        final Plant plant = new Plant(plantAddRequest.getName(), plantAddRequest.getImageUrl(),
                plantAddRequest.getLocation(), plantAddRequest.getLatitude(),
                plantAddRequest.getLongitude(), plantAddRequest.getDescription());
        plantRepository.save(plant);

        System.out.println(plant.getId());

        return plant.getId();
    }

    public IsPlantResponse isPlant(final String imageUrl) {
        final Boolean isPlant = true;   // TODO Plant 인지 아닌지 판단하는 함수 추가해줘
        final IsPlantResponse isPlantResponse = new IsPlantResponse(isPlant, imageUrl);

        return isPlantResponse;
    }
}
