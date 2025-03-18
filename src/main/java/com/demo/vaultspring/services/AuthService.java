package com.demo.vaultspring.services;

import com.demo.vaultspring.dto.auth.AuthResponseDTO;
import com.demo.vaultspring.dto.auth.LoginRequestDTO;
import com.demo.vaultspring.dto.auth.RegisterRequestDTO;
import com.demo.vaultspring.model.User;

import java.util.Optional;

public interface AuthService {
    AuthResponseDTO register(RegisterRequestDTO request);
    AuthResponseDTO login(LoginRequestDTO request);
    Optional<User> findByUsername(String username);
}
