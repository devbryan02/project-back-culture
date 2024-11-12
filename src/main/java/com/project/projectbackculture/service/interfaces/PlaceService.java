package com.project.projectbackculture.service.interfaces;

import com.project.projectbackculture.persistence.model.PlaceModel;
import com.project.projectbackculture.web.request.NewPlaceRequest;
import com.project.projectbackculture.web.response.PlaceResponse;

public interface PlaceService extends ServiceGeneral<PlaceResponse,NewPlaceRequest,Integer> {

    //methods
    void searchAndAddCategoryToPlace(NewPlaceRequest newPlaceRequest, PlaceModel placeModel);
}
