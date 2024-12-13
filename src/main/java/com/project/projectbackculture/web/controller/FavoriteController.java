package com.project.projectbackculture.web.controller;

import com.project.projectbackculture.service.implement.FavoriteServiceImpl;
import com.project.projectbackculture.web.response.FavoriteResponse;
import com.project.projectbackculture.web.response.UserFavorityResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/favority")
public class FavoriteController {

    private final FavoriteServiceImpl favoriteService;

    public FavoriteController(FavoriteServiceImpl favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping
    public ResponseEntity<FavoriteResponse> createFavorite(
            @RequestParam String username,
            @RequestParam Integer placeId){
        FavoriteResponse favoriteResponse = favoriteService.addFavorite(username, placeId);
        return new ResponseEntity<>(favoriteResponse, HttpStatus.CREATED);
    }

    @GetMapping(path = "/user")
    public ResponseEntity<List<UserFavorityResponse>> getUserFavority(@RequestParam String username){
        List<UserFavorityResponse> favorities = favoriteService.findFavouritesByUsername(username);
        return new ResponseEntity<>(favorities, HttpStatus.OK);
    }

}
