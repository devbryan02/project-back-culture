package com.project.projectbackculture.web.response;

import lombok.Builder;

import java.util.List;

@Builder
public record PlaceDetailsResponse(
        Integer placeId,
        String name,
        String location,
        String descripcion,
        Double qualificationAverage,
        List<String> images
) {
}
