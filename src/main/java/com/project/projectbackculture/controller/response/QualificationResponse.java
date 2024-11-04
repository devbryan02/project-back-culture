package com.project.projectbackculture.controller.response;


public record QualificationResponse(
        Integer QualificationId,
        Integer userId,
        Integer placeId,
        Integer punctuation
) { }
