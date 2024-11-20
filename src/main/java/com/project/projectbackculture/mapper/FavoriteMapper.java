package com.project.projectbackculture.mapper;

import com.project.projectbackculture.persistence.model.FavoriteModel;
import com.project.projectbackculture.web.response.FavoriteResponse;


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

    //Mapea de request a model


}
