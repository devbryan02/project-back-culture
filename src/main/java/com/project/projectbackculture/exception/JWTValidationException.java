package com.project.projectbackculture.exception;

public class JWTValidationException extends RuntimeException {

    public JWTValidationException(String message) {
        super(message);
    }
}
