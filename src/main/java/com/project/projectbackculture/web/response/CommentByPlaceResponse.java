package com.project.projectbackculture.web.response;

import lombok.Builder;

@Builder
public record CommentByPlaceResponse(
        Integer commentId,
        String username,
        String textComment,
        String commentDate
) { }
