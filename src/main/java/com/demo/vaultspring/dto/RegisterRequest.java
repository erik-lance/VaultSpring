package com.demo.vaultspring.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
}
