package com.demo.vaultspring.services;

import com.demo.vaultspring.dto.auth.AuthResponse;
import com.demo.vaultspring.dto.auth.LoginRequest;
import com.demo.vaultspring.dto.auth.RegisterRequest;
import com.demo.vaultspring.model.User;

import java.util.Optional;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    Optional<User> findByUsername(String username);
}
