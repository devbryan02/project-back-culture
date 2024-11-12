package com.project.projectbackculture.web.response;


import lombok.Builder;

@Builder
public record QualificationResponse(
        Integer qualificationId,
        Integer userId,
        Integer placeId,
        Integer punctuation
) { }
