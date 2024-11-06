package com.project.projectbackculture.controller.response;

import lombok.Builder;

@Builder
public record CommentResponse(
        Integer commentId,
        Integer userId,
        Integer placeId,
        String textComment,
        String commentDate
) {}