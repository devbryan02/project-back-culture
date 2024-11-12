package com.project.projectbackculture.web.controller;

import com.project.projectbackculture.service.implement.CategoryServiceImpl;
import com.project.projectbackculture.web.request.NewCategoryRequest;
import com.project.projectbackculture.web.response.CategoryResponse;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PatchMapping(path = "/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(
            @RequestBody NewCategoryRequest request,
            @PathVariable Integer id){
        CategoryResponse categoryResponse = categoryService.update(request, id);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<CategoryResponse>> findCategoryByid(@PathVariable Integer id){
        Optional<CategoryResponse> categoryResponse = categoryService.findById(id);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<CategoryResponse>> findAllCategories(){
        List<CategoryResponse> categoryResponse = categoryService.findAll();
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Integer id){
        categoryService.delete(id);
        return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
    }
}
