package com.project.projectbackculture.controller.response;

public record CategoryResponse(
        Integer categoryId,
        String categoryName,
        String descripcion,
        String message
) { }
