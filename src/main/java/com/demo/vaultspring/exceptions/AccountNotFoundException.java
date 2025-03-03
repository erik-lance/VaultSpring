package com.demo.vaultspring.exceptions;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException() {
        super("Account does not exist");
    }

    public AccountNotFoundException(Long id) {
        super("Account with id " + id + " does not exist");
    }
}
