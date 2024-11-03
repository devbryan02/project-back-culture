package com.project.projectbackculture.controller.response;

import com.project.projectbackculture.persistence.model.QualificationModel;

public record QualificationResponse(
        QualificationModel qualification,
        String message
) {
}
