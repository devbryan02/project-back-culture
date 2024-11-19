package com.project.projectbackculture.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.projectbackculture.service.implement.PhotoServiceImpl;
import com.project.projectbackculture.web.request.NewPhotoRequest;
import com.project.projectbackculture.web.response.PhotoResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
public class ImageController {

    private final PhotoServiceImpl photoService;

    public ImageController(PhotoServiceImpl photoService) {
        this.photoService = photoService;
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<PhotoResponse> createPhoto(
            @RequestPart("request") @Valid  String requestJson,
            @RequestPart("file") MultipartFile file,
            @RequestParam Integer placeId) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        NewPhotoRequest photoRequest = objectMapper.readValue(requestJson, NewPhotoRequest.class);
        PhotoResponse photoResponse = photoService.saveWithImage(photoRequest, file, placeId);
        return new ResponseEntity<>(photoResponse, HttpStatus.CREATED);
    }
}
