package com.project.projectbackculture.controller.response;

import com.project.projectbackculture.persistence.model.PlaceModel;

public record PlaceResponse(
        PlaceModel place,
        String message
) {
}
