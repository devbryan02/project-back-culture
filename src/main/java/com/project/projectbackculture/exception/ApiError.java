package com.project.projectbackculture.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record ApiError(
        String path,
        String message,
        Integer statusCode,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDateTime timestamp,
        List<String> errors
) { }
