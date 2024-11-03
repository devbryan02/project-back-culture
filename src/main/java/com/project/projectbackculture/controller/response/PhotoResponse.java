package com.project.projectbackculture.controller.response;

import com.project.projectbackculture.persistence.model.PhotoModel;

public record PhotoResponse(
        PhotoModel photo,
        String message
) {
}
