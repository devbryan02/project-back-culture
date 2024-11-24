package com.project.projectbackculture.web.controller;

import com.project.projectbackculture.service.implement.FavoriteServiceImpl;
import com.project.projectbackculture.web.response.FavoriteResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/favorite")
public class FavoriteController {

    private final FavoriteServiceImpl favoriteService;

    public FavoriteController(FavoriteServiceImpl favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping
    public ResponseEntity<FavoriteResponse> createFavorite(
            @RequestParam String usernaname,
            @RequestParam Integer placeId){
        FavoriteResponse favoriteResponse = favoriteService.addFavorite(usernaname, placeId);
        return new ResponseEntity<>(favoriteResponse, HttpStatus.CREATED);
    }

}
