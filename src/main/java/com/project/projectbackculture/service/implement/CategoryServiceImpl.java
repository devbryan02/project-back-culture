package com.project.projectbackculture.service.implement;


import com.project.projectbackculture.exception.CustomException;
import com.project.projectbackculture.web.request.NewCategoryRequest;
import com.project.projectbackculture.web.response.CategoryResponse;
import com.project.projectbackculture.mapper.CategoryMapper;
import com.project.projectbackculture.persistence.model.CategoryModel;
import com.project.projectbackculture.persistence.repository.CategoryRepository;
import com.project.projectbackculture.service.interfaces.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponse save(NewCategoryRequest request) {

        CategoryModel categoryModel = CategoryMapper.toModel(request);
        log.info("mapping requets to categoryModel");

        CategoryModel savedCategory = categoryRepository.save(categoryModel);
        log.info("saving categoryModel in database");

        return CategoryMapper.toResponse(savedCategory);
    }

    @Override
    public Optional<CategoryResponse> update(NewCategoryRequest request, Integer id) {

        if(id == null) throw  new CustomException("El id no debe ser nulo");

        return categoryRepository.findById(id)
                .map(category -> {
                    CategoryMapper.updateCategory(category, request);
                    categoryRepository.save(category);
                    return CategoryMapper.toResponse(category);
                });
    }

    @Override
    public void delete(Integer id) {
        if(id != null) categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<CategoryResponse> findById(Integer id) {

        if(id == null) return Optional.empty();

        return categoryRepository
                .findById(id)
                .map(CategoryMapper::toResponse);
    }
}
