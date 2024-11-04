package com.project.projectbackculture.controller.response;


public record CommentResponse(
        Integer commentId,
        Integer userId,
        Integer placeId,
        String textComment,
        String commentDate
) {}