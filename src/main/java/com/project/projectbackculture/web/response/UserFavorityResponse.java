package com.project.projectbackculture.web.response;

import lombok.Builder;

@Builder
public record UserFavorityResponse(
        Integer placeId,
        String name,
        String description,
        String location,
        String distance,
        String urlImage
) { }
