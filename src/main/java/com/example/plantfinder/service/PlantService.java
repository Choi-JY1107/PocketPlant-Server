package com.example.plantfinder.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.http.*;
import org.springframework.stereotype.Service;

import com.example.plantfinder.dto.IsPlantResponse;
import com.example.plantfinder.dto.PlantAddRequest;
import com.example.plantfinder.entity.Plant;
import com.example.plantfinder.repository.PlantRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PlantService {
    private final PlantRepository plantRepository;

    public List<Plant> getAllPlants() {
        var s = plantRepository.findAll();
        System.out.println(s);
        return s;
    }

    public Optional<Plant> getPlantById(final String id) {
        final Optional<Plant> plant = plantRepository.findById(id);

        return plant;
    }

    public String savePlant(final PlantAddRequest plantAddRequest) {
        final Plant plant = new Plant(plantAddRequest.getName(), plantAddRequest.getImageUrl(),
                plantAddRequest.getLocation(), plantAddRequest.getLatitude(),
                plantAddRequest.getLongitude(), plantAddRequest.getDescription());
        plantRepository.save(plant);

        System.out.println(plant.getId());

        return plant.getId();
    }

    public IsPlantResponse isPlant(final String imageUrl) {
        System.out.println("isPlant service Layer 호출");
        var splitArr = imageUrl.split("/");
        var imageUuid = splitArr[splitArr.length - 1];

        String url = "https://o3i6cdavx7.execute-api.ap-northeast-2.amazonaws.com/dev/detect";
        RestTemplate restTemplate = new RestTemplate();

        String jsonBody = "{ \"bucket\": \"flower-test-dataset\", \"name\": \""+imageUuid+"\"}";
        System.out.println(jsonBody);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);

        ArrayList response = restTemplate.postForObject(url, request, ArrayList.class);

        for (Object object : response) {
            if (object.toString().equals("Fruit")){
                return new IsPlantResponse(true, imageUrl);
            }
            else if(object.toString().equals("Plant")){
                return new IsPlantResponse(true, imageUrl);

            }
            else if(object.toString().equals("Flower")){
                return new IsPlantResponse(true, imageUrl);

            }


        }

        return new IsPlantResponse(false, imageUrl);
    }
}
