package com.demo.vaultspring.services;

import com.demo.vaultspring.model.User;
import com.demo.vaultspring.model.enums.Role;

import java.util.Optional;

public interface AuthService {
    User register(String username, String password, Role role);
    Optional<User> findByUsername(String username);
}
