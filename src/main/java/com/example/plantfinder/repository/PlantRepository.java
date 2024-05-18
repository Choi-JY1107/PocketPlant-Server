package com.example.plantfinder.repository;


import com.example.plantfinder.entity.Plant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlantRepository extends MongoRepository<Plant, String> {
}
