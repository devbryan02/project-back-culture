package com.project.projectbackculture.controller.request;

import jakarta.validation.constraints.NotNull;

public record NewCommentRequest(
        @NotNull(message = "userId is required") Integer userId,
        @NotNull(message = "placeId is required") Integer placeId,
        @NotNull(message = "textComment is required") String textComment
) { }
