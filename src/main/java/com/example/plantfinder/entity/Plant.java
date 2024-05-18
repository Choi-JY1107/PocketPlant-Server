package com.example.plantfinder.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;


@Document(collection = "plant")
@Getter
@ToString
@Setter
@Builder
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
    private String createdAt;
}