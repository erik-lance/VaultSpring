package com.demo.vaultspring.dto.user;

import com.demo.vaultspring.dto.account.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private List<AccountDTO> accounts; // Only necessary fields
}

