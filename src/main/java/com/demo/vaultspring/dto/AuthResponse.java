package com.demo.vaultspring.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    @NotBlank(message = "Token can not be blank")
    private String token;
}
