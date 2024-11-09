package com.project.projectbackculture.web.request;


import jakarta.validation.constraints.NotNull;

public record NewCategoryRequest(
        @NotNull(message = "categoryName is required") String categoryName,
        @NotNull(message = "description is required")  String description
) { }
