package com.project.projectbackculture.mapper;

import com.project.projectbackculture.controller.request.NewUserRequest;
import com.project.projectbackculture.controller.response.UserResponse;
import com.project.projectbackculture.persistence.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserModel toModel(NewUserRequest request);
    UserResponse toResponse(UserModel user);

}
