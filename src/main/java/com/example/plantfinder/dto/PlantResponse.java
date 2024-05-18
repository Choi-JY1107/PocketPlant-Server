package com.example.plantfinder.dto;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import com.example.plantfinder.entity.Plant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlantResponse {
    @Id
    private String id;
    private String name;
    private String imageUrl;
    private String location;
    private String latitude;
    private String longitude;
    private String description;
    private String createdAt;

    public PlantResponse(final Plant plant) {
        this.id = plant.getId();
        this.name = plant.getName();
        this.imageUrl = plant.getImageUrl();
        this.location = plant.getLocation();
        this.latitude = plant.getLatitude();
        this.longitude = plant.getLongitude();
        this.description = plant.getDescription();
        this.createdAt = convertCreatedTime(plant.getCreatedAt());
    }

    //yyyy-MM-dd HH:mm:ss
    private String convertCreatedTime(LocalDateTime createdAt) {
        String convertedCreatedTime = String.valueOf(createdAt);

        return convertedCreatedTime.substring(0, 19).replace("T", " ");
    }
}
