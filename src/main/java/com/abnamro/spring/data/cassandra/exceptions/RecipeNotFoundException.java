package com.abnamro.spring.data.cassandra.exceptions;

public class RecipeNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public RecipeNotFoundException(String message) {
        super(message);

    }
}
