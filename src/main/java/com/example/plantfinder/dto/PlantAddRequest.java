package com.example.plantfinder.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlantAddRequest {
    private String name;
    private String imageUrl;
    private String location;
    private String latitude;
    private String longitude;
    private String description;

}
