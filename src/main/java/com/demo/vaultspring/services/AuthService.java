package com.demo.vaultspring.services;

import com.demo.vaultspring.dto.AuthResponse;
import com.demo.vaultspring.dto.LoginRequest;
import com.demo.vaultspring.dto.RegisterRequest;
import com.demo.vaultspring.model.User;
import com.demo.vaultspring.model.enums.Role;

import java.util.Optional;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    Optional<User> findByUsername(String username);
}
