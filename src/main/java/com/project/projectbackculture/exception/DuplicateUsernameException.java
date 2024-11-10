package com.project.projectbackculture.exception;

public class DuplicateUsernameException extends RuntimeException {

    public DuplicateUsernameException(String username) {
        super("El username "+username+" ya esta en uso");
    }
}
