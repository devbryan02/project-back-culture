package com.project.projectbackculture.web.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AuthLoginRequest(
        @NotNull(message = "username is required") String username,
        @NotNull(message = "password is required") String password
) { }