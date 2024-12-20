package com.project.projectbackculture.service.interfaces;

import com.project.projectbackculture.persistence.model.PlaceModel;
import com.project.projectbackculture.persistence.model.UserModel;
import com.project.projectbackculture.web.request.NewFavoriteRequest;
import com.project.projectbackculture.web.response.DeleteFavoriteResponse;
import com.project.projectbackculture.web.response.FavoriteResponse;
import com.project.projectbackculture.web.response.UserFavorityResponse;

import java.util.List;


public interface FavoriteService extends ServiceGeneral<FavoriteResponse,NewFavoriteRequest,Integer> {

    // no hay mas metodos para  favorite model
    FavoriteResponse addFavorite(String username, Integer placeId);
    void checkExistingFavorite(Integer userId, Integer placeId);
    UserModel findByUsername(String username);
    PlaceModel findByPlaceId(Integer placeId);
    void validateIDRequest(String username, Integer placeId);
    List<UserFavorityResponse> findFavouritesByUsername(String username);
    DeleteFavoriteResponse deleteFavorite (String username, Integer placeId);

}
