package com.project.projectbackculture.mapper;

import com.project.projectbackculture.controller.request.NewFavoriteRequest;
import com.project.projectbackculture.controller.response.FavoriteResponse;
import com.project.projectbackculture.persistence.model.FavoriteModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FavoriteMapper {

    FavoriteMapper INSTANCE  = Mappers.getMapper(FavoriteMapper.class);

    FavoriteModel toModel(NewFavoriteRequest request);
    FavoriteResponse toResponse(FavoriteModel favorite);

}
