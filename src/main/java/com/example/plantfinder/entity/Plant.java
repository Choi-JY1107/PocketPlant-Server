package com.example.plantfinder.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "plant")
@Getter
@ToString
@Setter
public class Plant {
    @Id
    private String id;

    // 식물 이름
    private String name;
    // 이미지 주소
    private String imageUrl;
    // 식물 발견한 위치
    private String location;
    // 위도
    private String latitude;
    // 경도
    private String longitude;
    // 설명
    private String description;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    public Plant(String name, String imageUrl, String location, String latitude, String longitude, String description) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }
}