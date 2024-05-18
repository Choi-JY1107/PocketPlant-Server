package com.example.plantfinder.dto;

public class PlantAddRequest {
    private String name;
    private String imageUrl;
    private String location;
    private String latitude;
    private String longitude;
    private String description;

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getLocation() {
        return location;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getDescription() {
        return description;
    }
}
