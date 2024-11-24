package com.project.projectbackculture.web.response;

import lombok.Builder;

@Builder
public record PlacePopularResponse(
        Integer placeId,
        String name,
        String location,
        String urlImage,
        double punctuationAverage
) { }
