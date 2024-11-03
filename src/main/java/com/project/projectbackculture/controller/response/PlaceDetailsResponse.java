package com.project.projectbackculture.controller.response;

import java.util.List;

public record PlaceDetailsResponse(
        String name,
        String location,
        String descripcion,
        Double qualificationAverage,
        List<String> images
) {
}
