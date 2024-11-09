package com.project.projectbackculture.web.response;


public record QualificationResponse(
        Integer QualificationId,
        Integer userId,
        Integer placeId,
        Integer punctuation
) { }
