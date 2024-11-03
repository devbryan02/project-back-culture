package com.project.projectbackculture.controller.request;

import jakarta.validation.constraints.NotNull;

public record PhotoRegistrationRequest(
        @NotNull(message = "description is required") String description
) {
}
