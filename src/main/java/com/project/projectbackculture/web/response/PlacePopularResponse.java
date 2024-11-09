package com.project.projectbackculture.web.response;

public record PlacePopularResponse(
        Integer placeId,
        String name,
        String location,
        String urlImage
) { }
