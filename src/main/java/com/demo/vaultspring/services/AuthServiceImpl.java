package com.demo.vaultspring.services;

import com.demo.vaultspring.dto.auth.AuthResponse;
import com.demo.vaultspring.dto.auth.LoginRequest;
import com.demo.vaultspring.dto.auth.RegisterRequest;
import com.demo.vaultspring.exceptions.UserNotFoundException;
import com.demo.vaultspring.exceptions.UsernameExistsException;
import com.demo.vaultspring.model.User;
import com.demo.vaultspring.model.enums.Role;
import com.demo.vaultspring.repositories.UserRepository;
import com.demo.vaultspring.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UsernameExistsException();
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRole(Role.USER);
        userRepository.save(user);

        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserDetails user = (UserDetails) userRepository.findByUsername(request.getUsername())
                .orElseThrow(UserNotFoundException::new);

        return null;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
