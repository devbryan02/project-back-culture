package com.project.projectbackculture.mapper;

import com.project.projectbackculture.persistence.model.ImageModel;
import com.project.projectbackculture.persistence.model.PlaceModel;
import com.project.projectbackculture.web.request.NewPlaceRequest;
import com.project.projectbackculture.web.response.PlacePopularResponse;
import com.project.projectbackculture.web.response.PlaceResponse;

import java.util.List;
import java.util.Optional;

public class PlaceMapper {

    //Mapear modelo a response
    public static PlaceResponse toResponse(PlaceModel placeModel) {

        if (placeModel == null) return null;

        return PlaceResponse.builder()
                .placeId(placeModel.getPlaceId())
                .name(placeModel.getName())
                .description(placeModel.getDescription())
                .location(placeModel.getLocation())
                .distance(placeModel.getDistance())
                .build();
    }

    public static PlacePopularResponse toPopularResponse(PlaceModel placeModel) {

        if (placeModel == null) return null;

        // Manejo seguro de la URL de la imagen
        String imageUrl = Optional.ofNullable(placeModel.getImages())
                .filter( images -> !images.isEmpty())
                .map(List::getFirst)
                .map(ImageModel::getSecureUrl)
                .orElse("Not found image");

        return PlacePopularResponse.builder()
                .placeId(placeModel.getPlaceId())
                .name(placeModel.getName())
                .location(placeModel.getLocation())
                .urlImage(imageUrl)
                .punctuationAverage(placeModel.getPunctuationAverage())
                .build();
    }

    //Mapear request a model
    public static PlaceModel toModel(NewPlaceRequest request) {

        if (request == null) return null;

        return PlaceModel.builder()
                .name(request.name())
                .description(request.description())
                .location(request.location())
                .distance(request.distance())
                .build();
    }

}
