package com.project.projectbackculture.web.response;


public record FavoriteResponse(
        Integer favoriteId,
        Integer userId,
        Integer placeId,
        String savedDate
) {
}
