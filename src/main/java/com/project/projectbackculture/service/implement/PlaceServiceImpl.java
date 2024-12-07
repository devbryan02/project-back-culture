package com.project.projectbackculture.service.implement;

import com.project.projectbackculture.exception.CategoryNotFoundException;
import com.project.projectbackculture.exception.CustomException;
import com.project.projectbackculture.mapper.PlaceMapper;
import com.project.projectbackculture.persistence.model.CategoryModel;
import com.project.projectbackculture.persistence.model.PlaceModel;
import com.project.projectbackculture.persistence.repository.CategoryRepository;
import com.project.projectbackculture.persistence.repository.PlaceRepository;
import com.project.projectbackculture.service.interfaces.PlaceService;
import com.project.projectbackculture.web.request.NewPlaceRequest;
import com.project.projectbackculture.web.response.PlaceDetailsResponse;
import com.project.projectbackculture.web.response.PlacePopularResponse;
import com.project.projectbackculture.web.response.PlaceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@Slf4j
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public PlaceResponse save(NewPlaceRequest request) {
        log.info("Saving new place: {}", request);

        // Mapea el DTO a una entidad
        PlaceModel placeModel = PlaceMapper.toModel(request);

        // Busca y asigna categorías
        Set<CategoryModel> categories = fetchCategoriesByIds(request.categoryIds());
        placeModel.setCategories(categories);

        // Guarda el lugar y mapea la respuesta
        PlaceModel savedPlace = placeRepository.save(placeModel);
        return PlaceMapper.toResponse(savedPlace);
    }

    @Override
    public Set<CategoryModel> fetchCategoriesByIds(Set<Integer> categoryIds) {
        if (categoryIds == null || categoryIds.isEmpty()) {
            return new HashSet<>();
        }
        List<CategoryModel> categories = categoryRepository.findAllById(categoryIds);
        if (categories.size() != categoryIds.size()) {
            throw new CategoryNotFoundException("Some categories not found: " + categoryIds);
        }
        return new HashSet<>(categories);
    }

    @Override
    @Transactional
    public List<PlacePopularResponse> findAllOrderedByPunctuation() {
        return placeRepository.findAllOrderedByPunctuation()
                .stream()
                .map(PlaceMapper::toPopularResponse)
                .toList();
    }

    @Override
    @Transactional
    public PlaceDetailsResponse getPlaceDetailsById(Integer placeId) {

        PlaceModel placeDetailsById = placeRepository.findById(placeId)
                .orElseThrow(() -> new CustomException("Place not fount"));

        return PlaceMapper.toDetailsResponse(placeDetailsById);

    }

    @Override
    public List<PlaceResponse> searchByKeyword(String keyword) {
        List<PlaceModel> placeModelList = placeRepository.searchByKerword(keyword);
        log.info("place buscado {}", placeModelList);
        return placeModelList.stream()
                .map(PlaceMapper::toResponse)
                .toList();
    }


    @Override
    public PlaceResponse update(NewPlaceRequest request, Integer id) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public List<PlaceResponse> findAll() {
        return List.of();
    }

    @Override
    public Optional<PlaceResponse> findById(Integer id) {
        return Optional.empty();
    }

}

