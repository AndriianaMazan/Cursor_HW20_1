package com.example.library.exceptions;

public class AuthorNotFoundException extends Exception {
    public AuthorNotFoundException(String message) {
        super(message);
    }
}
