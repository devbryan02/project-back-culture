package com.project.projectbackculture.web.response;

import lombok.Builder;

@Builder
public record CategoryResponse(
        Integer categoryId,
        String categoryName,
        String descripcion
) { }
