package com.project.projectbackculture.controller.response;

import com.project.projectbackculture.persistence.model.UserModel;

public record UserResponse(
        UserModel user,
        String message
) {
}
