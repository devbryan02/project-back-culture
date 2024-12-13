package com.project.projectbackculture.web.response;

public record ErrorAuthenticationResponse(

        String error,
        String message,
        boolean success
) { }
