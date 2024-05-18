package com.example.plantfinder.controller;

import com.example.plantfinder.entity.Plant;
import com.example.plantfinder.service.PlantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/plant")
@RequiredArgsConstructor
public class PlantController {

    private final PlantService plantService;

    @GetMapping
    public List<Plant> getAllPlants() {
        return plantService.getAllPlants();
    }
}
