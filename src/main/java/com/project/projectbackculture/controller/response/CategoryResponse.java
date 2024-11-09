package com.project.projectbackculture.controller.response;

import lombok.Builder;

@Builder
public record CategoryResponse(
        Integer categoryId,
        String categoryName,
        String descripcion
) { }
