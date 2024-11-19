package com.project.projectbackculture.web.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewPhotoRequest(
        @NotNull(message = "descripcion no debe ser nulo")
        @NotEmpty(message = "descripcion no debe estar vacio")
        String description
) {
}
