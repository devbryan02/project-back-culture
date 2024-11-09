package com.project.projectbackculture.mapper;

import com.project.projectbackculture.web.request.NewUserRequest;
import com.project.projectbackculture.web.response.UserResponse;
import com.project.projectbackculture.persistence.model.UserModel;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserMapper {

    // mapear modelo a response
    public static UserResponse toResponse(UserModel userModel) {

        if(userModel == null) return null;

        return UserResponse.builder()
                .userId(userModel.getUserId())
                .email(userModel.getEmail())
                .fullName(userModel.getFullName())
                .build();
    }

    // Mapear request a model
    public static UserModel toModel(NewUserRequest userRequest, PasswordEncoder passwordEncoder) {

        if(userRequest == null) return null;

        return UserModel.builder()
                .email(userRequest.email())
                .username(userRequest.username())
                .password(passwordEncoder.encode(userRequest.password()))
                .fullName(userRequest.fullName())
                .build();
    }

}
