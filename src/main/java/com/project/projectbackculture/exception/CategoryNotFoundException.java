package com.project.projectbackculture.exception;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(Integer id) {
        super("Category  with id " + id + " not found");
    }
}
