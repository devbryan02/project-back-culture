package com.project.projectbackculture.controller.response;

import lombok.Builder;

@Builder
public record UserResponse(
        Integer userId,
        String email,
        String fullName
) {
}
