package com.example.plantfinder.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.plantfinder.dto.PlantResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.plantfinder.dto.IsPlantResponse;
import com.example.plantfinder.dto.PlantAddRequest;
import com.example.plantfinder.dto.PlantResponse;
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

    public PlantResponse savePlant(final PlantAddRequest plantAddRequest) {
        final Plant plant = Plant.builder()
                .name(plantAddRequest.getName())
                .imageUrl(plantAddRequest.getImageUrl())
                .latitude(plantAddRequest.getLatitude())
                .longitude(plantAddRequest.getLongitude())
                .location(plantAddRequest.getLocation())
                .description(plantAddRequest.getDescription())
                .build();

        plantRepository.save(plant);


        return new PlantResponse(plant);
    }

    private String checkPlant(final String imageUrl){
        var splitArr = imageUrl.split("/");
        var imageUuid = splitArr[splitArr.length - 1];

        String url = "https://o3i6cdavx7.execute-api.ap-northeast-2.amazonaws.com/dev/custom";
        RestTemplate restTemplate = new RestTemplate();

        String jsonBody = "{ \"bucket\": \"flower-test-dataset\", \"name\": \""+imageUuid+"\"}";


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);

        String response = restTemplate.postForObject(url, request, String.class);
        return response;

    }

    public IsPlantResponse isPlant(final String imageUrl) {
        var splitArr = imageUrl.split("/");
        var imageUuid = splitArr[splitArr.length - 1];
        var isPlant = false;
        String url = "https://o3i6cdavx7.execute-api.ap-northeast-2.amazonaws.com/dev/detect";
        RestTemplate restTemplate = new RestTemplate();

        String jsonBody = "{ \"bucket\": \"flower-test-dataset\", \"name\": \""+imageUuid+"\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);

        ArrayList response = restTemplate.postForObject(url, request, ArrayList.class);

        for (Object object : response) {
            if (object.toString().equals("Fruit")
                    || object.toString().equals("Plant")
                    || object.toString().equals("Flower")) {
                checkPlant(imageUrl);
                isPlant = true;
                break;

            }

        }

        if (isPlant){
            var name = checkPlant(imageUrl);
            return new IsPlantResponse(true, imageUrl, name, Description.plantDescriptions.get(name));
        }
        return new IsPlantResponse(false, imageUrl, null, null);
    }

    public List<PlantResponse> getPlantsByLocation(String location){
        List<PlantResponse> plantResList= new ArrayList<>();
        var plantList = plantRepository.findPlantByLocation(location);

        for(Plant plant: plantList){
            var x = new PlantResponse(plant);
            plantResList.add(x);
        }
        return plantResList;
    }
}
