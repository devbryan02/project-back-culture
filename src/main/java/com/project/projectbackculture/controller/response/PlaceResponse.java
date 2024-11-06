package com.project.projectbackculture.controller.response;


import lombok.Builder;

@Builder
public record PlaceResponse(
        Integer placeId,
        String name,
        String description,
        String location
) { }
