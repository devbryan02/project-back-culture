package com.project.projectbackculture.service.interfaces;

import com.project.projectbackculture.web.request.NewCategoryRequest;
import com.project.projectbackculture.web.response.CategoryResponse;

public interface CategoryService extends ServiceGeneral<CategoryResponse, NewCategoryRequest,Integer> {

    // no hay mas metodos para category model
    void validateUniqueConstrain(NewCategoryRequest newCategoryRequest);
}
