package com.example.plantfinder.repository;


import java.util.Collection;
import java.util.List;

import com.example.plantfinder.entity.Plant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlantRepository extends MongoRepository<Plant, String> {
    List<Plant> findByName(String type);
    List<Plant> findPlantByLocation(String location);
}
