package com.project.projectbackculture.controller.response;

import com.project.projectbackculture.persistence.model.FavoriteModel;

public record FavoriteResponse(
        FavoriteModel favorite,
        String message
) {
}
