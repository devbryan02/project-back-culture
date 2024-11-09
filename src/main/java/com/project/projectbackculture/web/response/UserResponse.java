package com.project.projectbackculture.web.response;

import lombok.Builder;

@Builder
public record UserResponse(
        Integer userId,
        String email,
        String fullName
) {
}
