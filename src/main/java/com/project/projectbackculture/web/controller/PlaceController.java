package com.project.projectbackculture.web.controller;

import com.project.projectbackculture.service.implement.PlaceServiceImpl;
import com.project.projectbackculture.web.request.NewPlaceRequest;
import com.project.projectbackculture.web.response.PlaceDetailsResponse;
import com.project.projectbackculture.web.response.PlacePopularResponse;
import com.project.projectbackculture.web.response.PlaceResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/place")
public class PlaceController {

    private final PlaceServiceImpl placeService;

    public PlaceController(PlaceServiceImpl placeService) {
        this.placeService = placeService;
    }

    @PostMapping
    public ResponseEntity<PlaceResponse> createPlace(@RequestBody @Valid
                                                         NewPlaceRequest newPlaceRequest) {
        PlaceResponse placeResponse = placeService.save(newPlaceRequest);
        return new ResponseEntity<>(placeResponse, HttpStatus.CREATED);
    }

    @GetMapping(path = "/popular")
    public ResponseEntity<List<PlacePopularResponse>> findPopularPlaces() {
        List<PlacePopularResponse> placeResponseList = placeService.findAllOrderedByPunctuation();
        return new ResponseEntity<>(placeResponseList, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PlaceDetailsResponse> getPlaceDetailsById(@PathVariable Integer id){
        PlaceDetailsResponse placeDetailsResponse = placeService.getPlaceDetailsById(id);
        return new ResponseEntity<>(placeDetailsResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/search")
    public ResponseEntity<List<PlaceResponse>> searchPlace(@RequestParam String keyword){
        List<PlaceResponse> placeResponseList = placeService.searchByKeyword(keyword);
        return new ResponseEntity<>(placeResponseList, HttpStatus.OK);
    }

    @GetMapping(path = "/filter/{category}")
    public ResponseEntity<List<PlaceResponse>> findPlaceByCategory(@PathVariable String category){
        List<PlaceResponse> placeResponseList = placeService.findPlaceByCategory(category);
        return new ResponseEntity<>(placeResponseList, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<PlacePopularResponse>> findPlaceByCategoryAndProvince(
            @RequestParam String province){
        var placeResponseList = placeService.findPlaceByProvince(province);
        return new ResponseEntity<>(placeResponseList, HttpStatus.OK);
    }
}
