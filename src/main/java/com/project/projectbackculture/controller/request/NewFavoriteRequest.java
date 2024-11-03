package com.project.projectbackculture.controller.request;

import jakarta.validation.constraints.NotNull;

public record NewFavoriteRequest(
        @NotNull(message = "userId is required") Integer userId,
        @NotNull(message = "userId is required") Integer placeId
) {
}
