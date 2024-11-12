package com.project.projectbackculture.web.controller;

import com.project.projectbackculture.service.implement.PlaceServiceImpl;
import com.project.projectbackculture.web.request.NewPlaceRequest;
import com.project.projectbackculture.web.response.PlaceResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
