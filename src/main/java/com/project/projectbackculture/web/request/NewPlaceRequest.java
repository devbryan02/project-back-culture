package com.project.projectbackculture.web.request;

import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record NewPlaceRequest(
        @NotNull(message = "name is required") String name,
        @NotNull(message = "description is description") String description,
        @NotNull(message = "location is location") String location,
        @NotNull(message = "distance is required") String distance,
        @NotNull(message = "categoryIds is required") Set<Integer> categoryIds
) { }
