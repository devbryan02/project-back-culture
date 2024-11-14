package com.project.projectbackculture.web.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record NewUserRequest(

        @Email(message = "El formato del email no es valido")
        @NotEmpty(message = "email no debe estar vacio")
        @NotNull(message = "email no debe ser nulo")
        String email,

        @NotNull(message = "username no debe ser nulo")
        @NotEmpty(message = "username no debe estar vacio")
        String username,

        @NotNull(message = "password no debe ser nulo")
        @NotEmpty(message = "password no debe ser nulo")
        String password,

        @NotNull(message = "fullname no debe ser nulo")
        @NotEmpty(message = "fullname no debe ser nulo")
        String fullName
) { }
