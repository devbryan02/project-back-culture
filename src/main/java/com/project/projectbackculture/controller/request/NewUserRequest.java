package com.project.projectbackculture.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;


public record NewUserRequest(
        @Email(message = "email format invalid") String email,
        @NotNull String password,
        @NotNull String fullName
) { }
