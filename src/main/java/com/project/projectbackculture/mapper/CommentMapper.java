package com.project.projectbackculture.mapper;

import com.project.projectbackculture.controller.request.NewCommentRequest;
import com.project.projectbackculture.controller.response.CommentResponse;
import com.project.projectbackculture.persistence.model.CommentModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    CommentModel toModel(NewCommentRequest request);
    CommentResponse toResponse(CommentModel comment);

}
