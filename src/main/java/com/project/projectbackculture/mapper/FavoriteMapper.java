package com.project.projectbackculture.mapper;

import com.project.projectbackculture.persistence.model.FavoriteModel;
import com.project.projectbackculture.persistence.model.ImageModel;
import com.project.projectbackculture.persistence.model.PlaceModel;
import com.project.projectbackculture.web.response.FavoriteResponse;
import com.project.projectbackculture.web.response.UserFavorityResponse;

import java.util.List;
import java.util.Optional;


public class FavoriteMapper {

    // mapea model a response
    public static FavoriteResponse toResponse(FavoriteModel favoriteModel) {

        if(favoriteModel == null) return null;

        return FavoriteResponse.builder()
                .favoriteId(favoriteModel.getFavoriteId())
                .userId(favoriteModel.getUser().getUserId())
                .placeId(favoriteModel.getPlace().getPlaceId())
                .savedDate(favoriteModel.getSavedDate())
                .build();
    }

    public static UserFavorityResponse favorityPlaceByUserResponse(PlaceModel placeModel){

        if(placeModel == null) return null;

        String imageUrl = Optional.ofNullable(placeModel.getImages())
                .filter( images -> !images.isEmpty())
                .map(List::getFirst)
                .map(ImageModel::getSecureUrl)
                .orElse("Not found image");


        return UserFavorityResponse.builder()
                .placeId(placeModel.getPlaceId())
                .namePlace(placeModel.getName())
                .description(placeModel.getDescription())
                .location(placeModel.getLocation())
                .distance(placeModel.getDistance())
                .urlImage(imageUrl)
                .build();
    }


}
