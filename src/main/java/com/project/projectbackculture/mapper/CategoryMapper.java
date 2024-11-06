package com.project.projectbackculture.mapper;

import com.project.projectbackculture.controller.request.NewCategoryRequest;
import com.project.projectbackculture.controller.response.CategoryResponse;
import com.project.projectbackculture.persistence.model.CategoryModel;

public class CategoryMapper {

    // Convetir model a response
    public static CategoryResponse toResponse(CategoryModel categoryModel) {

        if (categoryModel == null) return null;

        return CategoryResponse.builder()
                .categoryId(categoryModel.getCategoryId())
                .categoryName(categoryModel.getCategoryName())
                .descripcion(categoryModel.getDescription())
                .message("Registrado correctament")
                .build();
    }

    // convertir de request a model
    public static CategoryModel toModel(NewCategoryRequest newCategoryRequest) {

        if(newCategoryRequest == null) return null;

        return CategoryModel.builder()
                .categoryName(newCategoryRequest.categoryName())
                .description(newCategoryRequest.description())
                .build();
    }
}
