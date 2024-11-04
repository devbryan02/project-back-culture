package com.project.projectbackculture.controller.response;


public record PlaceResponse(
        Integer placeId,
        String name,
        String description,
        String location
) { }
