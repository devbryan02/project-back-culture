package com.project.projectbackculture.controller.response;

import com.project.projectbackculture.persistence.model.CommentModel;

public record CommentResponse(
        CommentModel comment,
        String message
) { }
