package com.project.projectbackculture.controller.response;

public record PhotoResponse(
        Integer photoId,
        Integer placeId,
        String  pathPhoto,
        String description,
        String uploadDate
) { }
