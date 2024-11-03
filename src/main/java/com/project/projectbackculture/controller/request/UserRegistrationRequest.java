package com.project.projectbackculture.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record UserRegistrationRequest(
        @Email(message = "email format invalid") String email,
        @NotNull() String password,
        @NotEmpty String fullName
) { }
