package com.project.projectbackculture.web.response;


import lombok.Builder;

import java.time.LocalDate;

@Builder
public record FavoriteResponse(
        Integer favoriteId,
        Integer userId,
        Integer placeId,
        LocalDate savedDate
) {
}
