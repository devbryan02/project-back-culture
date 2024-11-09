package com.project.projectbackculture.web.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username", "message","success", "token"})
public record AuthLoginResponse(
        String username,
        String message,
        String token,
        boolean success
) { }
