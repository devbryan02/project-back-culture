package com.project.projectbackculture.web.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AuthLoginRequest(

        @NotEmpty(message = "username no debe estar vacio")
        @NotNull(message = "username no debe ser nulo")
        String username,

        @NotEmpty(message = "password no debe estar vacio")
        @NotNull(message = "password no debe ser nulo")
        String password

) { }
