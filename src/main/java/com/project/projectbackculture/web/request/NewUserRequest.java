package com.project.projectbackculture.web.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;


public record NewUserRequest(
        @Email(message = "email format invalid") String email,
        @NotNull String username,
        @NotNull String password,
        @NotNull String fullName
) { }
