package com.project.projectbackculture;

import com.project.projectbackculture.web.request.NewCategoryRequest;
import com.project.projectbackculture.web.response.CategoryResponse;
import com.project.projectbackculture.service.implement.CategoryServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ProjectBackCultureApplication {

    private final CategoryServiceImpl categoryService;

    public ProjectBackCultureApplication(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProjectBackCultureApplication.class, args);
    }


    @PostMapping
    public ResponseEntity<CategoryResponse>  createCategory(
            @RequestBody NewCategoryRequest newCategoryRequest
            ){
        CategoryResponse response = categoryService.save(newCategoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
