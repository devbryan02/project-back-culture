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
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public CategoryResponse save(NewCategoryRequest request) {

        log.info("Iniciando registro de categoria: {}", request);

        //Valida la existencia de una categoria
        validateUniqueConstrain(request);

        CategoryModel categoryModel = CategoryMapper.toModel(request);
        log.info("mapping requets to categoryModel");

        CategoryModel savedCategory = categoryRepository.save(categoryModel);
        log.info("saving categoryModel in database");

        return CategoryMapper.toResponse(savedCategory);
    }

    @Override
    @Transactional
    public CategoryResponse update(NewCategoryRequest request, Integer id) {

        validateUniqueConstrain(request);
        //Busca el modelo existente por su id
        Optional<CategoryModel> categoryModelOptional = categoryRepository.findById(id);

        //Lanza una exception en caso de no encontrar el modelo
        if(categoryModelOptional.isEmpty()) throw new CustomException("Categoria no encontrada");

        // Obtiene el modelo existencia y actualiza sus datos
        CategoryModel categoryModel = categoryModelOptional.get();
        CategoryMapper.updateCategory(categoryModel, request);

        //Guarda el modelo actualizado en base de datos
        CategoryModel savedCategory = categoryRepository.save(categoryModel);

        return CategoryMapper.toResponse(savedCategory);
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

    @Override
    public void validateUniqueConstrain(NewCategoryRequest newCategoryRequest) {
        if(categoryRepository.existsByCategoryName(newCategoryRequest.categoryName()))
            throw new CustomException("La categoria "+newCategoryRequest.categoryName()+" ya existe");
    }
}
