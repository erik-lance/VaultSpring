package com.demo.vaultspring.services;

import com.demo.vaultspring.model.Account;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AccountService {
    Account createAccount(Account account);
    Optional<Account> findAccountById(Long id);
    List<Account> getAllAccounts();
    void deleteAccount(Long id);

    Account deposit(Long accountId, BigDecimal amount);
    Account withdraw(Long accountId, BigDecimal amount);
}
