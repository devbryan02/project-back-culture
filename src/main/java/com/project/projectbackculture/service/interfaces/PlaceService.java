package com.project.projectbackculture.service.interfaces;

import com.project.projectbackculture.persistence.model.CategoryModel;
import com.project.projectbackculture.persistence.model.PlaceModel;
import com.project.projectbackculture.web.request.NewPlaceRequest;
import com.project.projectbackculture.web.response.PlacePopularResponse;
import com.project.projectbackculture.web.response.PlaceResponse;

import java.util.List;
import java.util.Set;

public interface PlaceService extends ServiceGeneral<PlaceResponse,NewPlaceRequest,Integer> {

    //methods
    Set<CategoryModel> fetchCategoriesByIds(Set<Integer> categoryIds);
    List<PlacePopularResponse> findAllOrderedByPunctuation();

}
