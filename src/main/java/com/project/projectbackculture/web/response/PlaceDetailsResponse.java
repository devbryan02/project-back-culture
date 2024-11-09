package com.project.projectbackculture.web.response;

import java.util.List;

public record PlaceDetailsResponse(
        Integer placeId,
        String name,
        String location,
        String descripcion,
        Double qualificationAverage,
        List<String> images
) {
}
