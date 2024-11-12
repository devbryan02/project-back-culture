package com.project.projectbackculture.mapper;

import com.project.projectbackculture.persistence.model.PlaceModel;
import com.project.projectbackculture.web.request.NewPlaceRequest;
import com.project.projectbackculture.web.response.PlaceResponse;

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
