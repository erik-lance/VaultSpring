package com.demo.vaultspring.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDTO {
    @NotBlank(message = "Token can not be blank")
    private String token;
}
