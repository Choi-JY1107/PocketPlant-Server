package com.example.plantfinder.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.plantfinder.dto.PlantAddRequest;
import com.example.plantfinder.entity.Plant;
import com.example.plantfinder.service.PlantService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/plant")
@RequiredArgsConstructor
public class PlantController {

    private final PlantService plantService;

    @GetMapping
    public List<Plant> getAllPlants() {
        List<Plant> plantList = plantService.getAllPlants();

        return plantList;
    }

    @GetMapping("/{id}")
    public Optional<Plant> getPlantById(@PathVariable final String id) {
        Optional<Plant> plant = plantService.getPlantById(id);

        return plant;
    }

    @PostMapping
    public String createPlant(@RequestBody final PlantAddRequest plantAddRequest) {
        String plantId = plantService.savePlant(plantAddRequest);

        return plantId;
    }
}
