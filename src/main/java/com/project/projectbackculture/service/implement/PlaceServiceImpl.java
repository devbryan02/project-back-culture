package com.project.projectbackculture.service.implement;

import com.project.projectbackculture.exception.CategoryNotFoundException;
import com.project.projectbackculture.mapper.PlaceMapper;
import com.project.projectbackculture.persistence.model.CategoryModel;
import com.project.projectbackculture.persistence.model.PlaceModel;
import com.project.projectbackculture.persistence.repository.CategoryRepository;
import com.project.projectbackculture.persistence.repository.PlaceRepository;
import com.project.projectbackculture.service.interfaces.PlaceService;
import com.project.projectbackculture.web.request.NewPlaceRequest;
import com.project.projectbackculture.web.response.PlaceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final CategoryRepository categoryRepository;

    public PlaceServiceImpl(PlaceRepository placeRepository, CategoryRepository categoryRepository) {
        this.placeRepository = placeRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public PlaceResponse save(NewPlaceRequest request) {

        log.info("Saving new place: {}", request);

        PlaceModel placeModel = PlaceMapper.toModel(request);

        //Busca y agrega cada categoria si existe al place
        searchAndAddCategoryToPlace(request, placeModel);

        // Guarda el place en la base de datos
        PlaceModel savedPlace = placeRepository.save(placeModel);

        return PlaceMapper.toResponse(savedPlace);
    }

    @Override
    public PlaceResponse update(NewPlaceRequest request, Integer integer) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public List<PlaceResponse> findAll() {
        return List.of();
    }

    @Override
    public Optional<PlaceResponse> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public void searchAndAddCategoryToPlace(NewPlaceRequest request, PlaceModel placeModel) {
        //Creamos un nuevo set para las categorias
        Set<CategoryModel> categories = new HashSet<>();

        //Buscar y agregar cada categoria si existe
        if(request.categoryIds() != null && !request.categoryIds().isEmpty()){
            for(Integer categoryId : request.categoryIds()) {
                CategoryModel  categoryModel = categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new CategoryNotFoundException(categoryId));
                categories.add(categoryModel);
                log.info("Loanding category: {}", categoryModel);
            }
        }

        //Asigna las categorias a place
        placeModel.setCategories(categories);
    }
}
