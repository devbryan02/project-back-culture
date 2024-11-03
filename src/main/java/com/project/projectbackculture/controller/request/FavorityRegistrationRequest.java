package com.project.projectbackculture.controller.request;

import jakarta.validation.constraints.NotNull;

public record FavorityRegistrationRequest(
        @NotNull(message = "userId is required") Integer userId,
        @NotNull(message = "userId is required") Integer placeId
) {
}
