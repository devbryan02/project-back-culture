package com.project.projectbackculture.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ApiError(
        String path,
        String message,
        Integer statusCode,
        LocalDateTime timestamp,
        List<String> errors
) { }
