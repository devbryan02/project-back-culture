package com.project.projectbackculture.web.controller;

import com.project.projectbackculture.service.implement.CategoryServiceImpl;
import com.project.projectbackculture.web.request.NewCategoryRequest;
import com.project.projectbackculture.web.response.CategoryResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody @Valid
                                                               NewCategoryRequest newCategoryRequest){
        CategoryResponse categoryResponse = categoryService.save(newCategoryRequest);
        return new ResponseEntity<>(categoryResponse, HttpStatus.CREATED);
    }
}
