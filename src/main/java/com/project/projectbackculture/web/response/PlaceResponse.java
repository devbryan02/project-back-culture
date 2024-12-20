package com.project.projectbackculture.web.response;


import lombok.Builder;

@Builder
public record PlaceResponse(
        Integer placeId,
        String name,
        String description,
        String location,
        String provicnce,
        String distance,
        String urlImage
) { }
