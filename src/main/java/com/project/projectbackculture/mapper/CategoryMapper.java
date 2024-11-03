package com.project.projectbackculture.mapper;

import com.project.projectbackculture.controller.request.NewCategoryRequest;
import com.project.projectbackculture.controller.response.CategoryResponse;
import com.project.projectbackculture.persistence.model.CategoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper  INSTANCE  = Mappers.getMapper(CategoryMapper.class);

    CategoryModel toModel(NewCategoryRequest request);
    CategoryResponse toResponse(CategoryModel category);

}
