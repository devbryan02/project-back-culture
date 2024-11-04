package com.project.projectbackculture.controller.response;


public record FavoriteResponse(
        Integer favoriteId,
        Integer userId,
        Integer placeId,
        String savedDate
) {
}
