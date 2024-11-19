package com.project.projectbackculture.web.response;

import lombok.Builder;

@Builder
public record PhotoResponse(
        Integer photoId,
        Integer placeId,
        String  pathPhoto,
        String description
) { }
