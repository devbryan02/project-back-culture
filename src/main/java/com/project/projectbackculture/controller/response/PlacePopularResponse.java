package com.project.projectbackculture.controller.response;

public record PlacePopularResponse(
        Integer placeId,
        String name,
        String location,
        String urlImage
) { }
