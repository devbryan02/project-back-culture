package com.project.projectbackculture.exception;

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(String email) {
        super("El email "+ email+" ya existe.");
    }
}
