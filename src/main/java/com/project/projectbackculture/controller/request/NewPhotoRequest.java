package com.project.projectbackculture.controller.request;

import jakarta.validation.constraints.NotNull;

public record NewPhotoRequest(
        @NotNull(message = "description is required") String description
) {
}
