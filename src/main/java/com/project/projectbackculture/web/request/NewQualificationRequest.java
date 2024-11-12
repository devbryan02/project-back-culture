package com.project.projectbackculture.web.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record NewQualificationRequest(
        @NotNull(message = "userId is required") Integer userId,
        @NotNull(message = "placeId is required") Integer placeId,
        @NotNull(message = "La puntuación es obligatoria")
        @Min(value = 1, message = "La puntuación mínima es 1")
        @Max(value = 5, message = "La puntuación máxima es 5")
        Integer punctuation
) { }
