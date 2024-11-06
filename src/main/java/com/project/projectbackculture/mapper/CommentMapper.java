package com.project.projectbackculture.mapper;

import com.project.projectbackculture.controller.request.NewCommentRequest;
import com.project.projectbackculture.controller.response.CommentResponse;
import com.project.projectbackculture.persistence.model.CommentModel;

import java.time.LocalDate;

public class CommentMapper {

    // Convertir model a response
    public static CommentResponse toResponse(CommentModel commentModel) {

        if(commentModel == null) return null;

        return CommentResponse.builder()
                .commentId(commentModel.getCommentId())
                .userId(commentModel.getUser().getUserId())
                .placeId(commentModel.getPlace().getPlaceId())
                .textComment(commentModel.getTextComment())
                .commentDate(commentModel.getCommentDate().toString())
                .build();
    }

    //Convertir request a model
    public static CommentModel toModel(NewCommentRequest newCommentRequest) {

        if(newCommentRequest == null) return null;

        return CommentModel.builder()
                .textComment(newCommentRequest.textComment())
                .commentDate(LocalDate.now())
                .build();

    }
}
