package com.project.projectbackculture.controller.response;

import com.project.projectbackculture.persistence.model.CategoryModel;

public record CategoryResponse(
        CategoryModel category,
        String message
) {
}
