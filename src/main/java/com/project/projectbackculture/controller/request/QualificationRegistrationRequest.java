package com.project.projectbackculture.controller.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record QualificationRegistrationRequest(
        @NotNull(message = "userId is required") Integer userId,
        @NotNull(message = "placeId is required") Integer placeId,
        @NotNull @Min(1) @Max(5) Integer punctuation
) { }
