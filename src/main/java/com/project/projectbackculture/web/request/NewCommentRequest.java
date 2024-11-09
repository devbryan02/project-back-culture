package com.project.projectbackculture.web.request;

import jakarta.validation.constraints.NotNull;

public record NewCommentRequest(
        @NotNull(message = "textComment is required") String textComment
) { }
