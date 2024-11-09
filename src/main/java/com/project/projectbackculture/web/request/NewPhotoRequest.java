package com.project.projectbackculture.web.request;

import jakarta.validation.constraints.NotNull;

public record NewPhotoRequest(
        @NotNull(message = "description is required") String description
) {
}
