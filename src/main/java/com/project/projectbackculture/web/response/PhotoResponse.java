package com.project.projectbackculture.web.response;

public record PhotoResponse(
        Integer photoId,
        Integer placeId,
        String  pathPhoto,
        String description,
        String uploadDate
) { }
