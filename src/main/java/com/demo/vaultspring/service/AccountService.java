package com.demo.vaultspring.service;

import com.demo.vaultspring.model.Account;
import com.demo.vaultspring.model.User;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Account createAccount(Account account);
    Optional<Account> findAccountById(Long id);
    List<Account> getAllAccounts();
    void deleteAccount(Long id);
}
