package com.example.plantfinder.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.plantfinder.dto.IsPlantResponse;
import com.example.plantfinder.dto.PlantAddRequest;
import com.example.plantfinder.entity.Plant;
import com.example.plantfinder.service.PlantService;
import com.example.plantfinder.service.S3Service;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/plant")
@RequiredArgsConstructor
public class PlantController {

    private final PlantService plantService;
    private final S3Service s3Service;

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
        final String plantId = plantService.savePlant(plantAddRequest);

        return plantId;
    }

    @PostMapping("/upload")
    public IsPlantResponse uploadImage(MultipartFile file) {
        final String url = s3Service.upload(file);
        final IsPlantResponse isPlantResponse = plantService.isPlant(url);
        System.out.println(isPlantResponse.toString());
        return isPlantResponse;
    }

}
