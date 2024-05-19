package com.example.plantfinder.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IsPlantResponse {
    private Boolean isPlant;
    private String imageUrl;
    private String name;
    private String description;
}
