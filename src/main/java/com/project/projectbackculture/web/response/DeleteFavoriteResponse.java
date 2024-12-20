package com.project.projectbackculture.web.response;

import lombok.Builder;

@Builder
public record DeleteFavoriteResponse(
        String message,
        boolean success
){ }
