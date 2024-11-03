package com.project.projectbackculture.mapper;

import com.project.projectbackculture.controller.request.NewPlaceRequest;
import com.project.projectbackculture.controller.response.PlaceResponse;
import com.project.projectbackculture.persistence.model.PlaceModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlaceMapper {

    PlaceMapper INSTANCE = Mappers.getMapper(PlaceMapper.class);

    PlaceModel toModel(NewPlaceRequest request);
    PlaceResponse toResponse(PlaceModel place);

}
