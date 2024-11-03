package com.project.projectbackculture.mapper;

import com.project.projectbackculture.controller.request.NewPhotoRequest;
import com.project.projectbackculture.controller.response.FavoriteResponse;
import com.project.projectbackculture.persistence.model.PhotoModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PhotoMapper {

    PhotoMapper INSTANCE  = Mappers.getMapper(PhotoMapper.class);

    PhotoModel toModel(NewPhotoRequest request);
    FavoriteResponse toResponse(PhotoModel photo);

}
