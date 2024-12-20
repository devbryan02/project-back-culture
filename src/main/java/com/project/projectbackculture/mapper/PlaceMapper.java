package com.project.projectbackculture.mapper;

import com.project.projectbackculture.persistence.model.ImageModel;
import com.project.projectbackculture.persistence.model.PlaceModel;
import com.project.projectbackculture.web.request.NewPlaceRequest;
import com.project.projectbackculture.web.response.PlaceDetailsResponse;
import com.project.projectbackculture.web.response.PlacePopularResponse;
import com.project.projectbackculture.web.response.PlaceResponse;

import java.util.List;
import java.util.Optional;

public class PlaceMapper {

    //Mapear modelo a response
    public static PlaceResponse toResponse(PlaceModel placeModel) {

        if (placeModel == null) return null;

        String imageUrl = Optional.ofNullable(placeModel.getImages())
                .filter( images -> !images.isEmpty())
                .map(List::getFirst)
                .map(ImageModel::getSecureUrl)
                .orElse("Not found image");

        return PlaceResponse.builder()
                .placeId(placeModel.getPlaceId())
                .name(placeModel.getName())
                .description(placeModel.getDescription())
                .location(placeModel.getLocation())
                .provicnce(placeModel.getProvince())
                .distance(placeModel.getDistance())
                .urlImage(imageUrl)
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

        // redondear la puntuacion
        double average = placeModel.getPunctuationAverage();
        double roundedAverage = Math.round(average * 10) / 10.0;

        return PlacePopularResponse.builder()
                .placeId(placeModel.getPlaceId())
                .name(placeModel.getName())
                .location(placeModel.getLocation())
                .province(placeModel.getProvince())
                .urlImage(imageUrl)
                .punctuationAverage(roundedAverage)
                .build();
    }

    public static PlaceDetailsResponse toDetailsResponse(PlaceModel placeModel) {

        if(placeModel == null) return null;

        List<String> images = placeModel.getImages().stream()
                .map(ImageModel::getSecureUrl)
                .toList();

        return PlaceDetailsResponse.builder()
                .placeId(placeModel.getPlaceId())
                .name(placeModel.getName())
                .location(placeModel.getLocation())
                .province(placeModel.getProvince())
                .descripcion(placeModel.getDescription())
                .qualificationAverage(placeModel.getPunctuationAverage())
                .images(images)
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
