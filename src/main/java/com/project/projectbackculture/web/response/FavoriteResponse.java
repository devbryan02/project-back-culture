package com.project.projectbackculture.web.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record FavoriteResponse(
        Integer favoriteId,
        Integer userId,
        Integer placeId,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate savedDate
) {
}
