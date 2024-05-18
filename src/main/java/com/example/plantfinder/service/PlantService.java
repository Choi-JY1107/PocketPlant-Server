package com.example.plantfinder.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.plantfinder.dto.PlantResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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

    public List<PlantResponse> getAllPlants() {
        return plantRepository.findAll().stream().map(PlantResponse::new).toList();
    }

    public List<PlantResponse> getPlantByType(final String type) {
        return plantRepository.findByName(type).stream().map(PlantResponse::new).toList();
    }

    public PlantResponse getPlantById(final String id) {
        return new PlantResponse(plantRepository.findById(id).orElseThrow(() -> new RuntimeException("식물이 없어요")));
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
        var splitArr = imageUrl.split("/");
        var imageUuid = splitArr[splitArr.length - 1];

        String url = "https://o3i6cdavx7.execute-api.ap-northeast-2.amazonaws.com/dev/detect";
        RestTemplate restTemplate = new RestTemplate();

        String jsonBody = "{ \"bucket\": \"flower-test-dataset\", \"name\": \""+imageUuid+"\"}";

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
