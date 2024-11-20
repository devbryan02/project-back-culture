package com.project.projectbackculture.service.interfaces;

import com.project.projectbackculture.persistence.model.PlaceModel;
import com.project.projectbackculture.persistence.model.UserModel;
import com.project.projectbackculture.web.request.NewCommentRequest;
import com.project.projectbackculture.web.response.CommentResponse;

public interface ComentService extends ServiceGeneral<CommentResponse,NewCommentRequest,Integer> {

    // no tiene mas metodos el coment model
    CommentResponse addComment(NewCommentRequest request, Integer userId, Integer placeId);
    void validateIdsRequest(Integer userId, Integer placeId);
    PlaceModel findPlaceById(Integer placeId);
    UserModel findUserById(Integer userId);
    void checkExistingComment(Integer userId, Integer placeId);
}