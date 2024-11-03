package com.project.projectbackculture.controller.request;

import jakarta.validation.constraints.NotNull;

public record PlaceRegistrationRequest(
        @NotNull(message = "name is required") String name,
        @NotNull(message = "name is description") String description,
        @NotNull(message = "name is location") String location
) { }
