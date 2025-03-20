package com.demo.vaultspring.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private Long id;
    private BigDecimal balance;
    private Long userId; // Avoid exposing full User entity
}
