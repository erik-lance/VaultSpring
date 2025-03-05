package com.demo.vaultspring.exceptions;

public class UsernameExistsException extends RuntimeException {
    public UsernameExistsException() {
        super("Username already exists");
    }
}
