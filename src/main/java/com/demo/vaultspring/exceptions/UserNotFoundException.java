package com.demo.vaultspring.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found. User does not exist");
    }

    public UserNotFoundException(Long id) {
        super("User with id "+id+" not found. User does not exist");
    }
}
