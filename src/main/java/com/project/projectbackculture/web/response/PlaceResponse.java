package com.project.projectbackculture.web.response;


import lombok.Builder;

@Builder
public record PlaceResponse(
        Integer placeId,
        String name,
        String description,
        String location,
        String distance,
        Double punctuationAverage
) { }
