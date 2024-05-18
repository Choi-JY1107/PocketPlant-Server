package com.example.plantfinder.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.plantfinder.dto.IsPlantResponse;
import com.example.plantfinder.dto.PlantAddRequest;
import com.example.plantfinder.dto.PlantResponse;
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
    public List<PlantResponse> getAllPlants() {
        return plantService.getAllPlants();
    }

    @GetMapping("/{id}")
    public PlantResponse getPlantById(@PathVariable final String id) {
        return plantService.getPlantById(id);
    }

    @GetMapping("/type/{type}")
    public List<PlantResponse> getPlantsByType(@PathVariable final String type) {
        return plantService.getPlantByType(type);
    }

    @PostMapping
    public PlantResponse createPlant(@RequestBody final PlantAddRequest plantAddRequest) {
        return plantService.savePlant(plantAddRequest);
    }

    @PostMapping("/upload")
    public IsPlantResponse uploadImage(MultipartFile file) {
        final String url = s3Service.upload(file);
        return plantService.isPlant(url);
    }

    @GetMapping("/location/{location}")
    public List<PlantResponse> getPlantsByLocation(@PathVariable String location) {
        return plantService.getPlantsByLocation(location);
    }

}
