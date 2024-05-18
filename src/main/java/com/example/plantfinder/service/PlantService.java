package com.example.plantfinder.service;

import com.example.plantfinder.entity.Plant;
import com.example.plantfinder.repository.PlantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlantService {
    private final PlantRepository plantRepository;

    public List<Plant> getAllPlants() {
        var s = plantRepository.findAll();
        System.out.println(s);
        return s;
    }
}
