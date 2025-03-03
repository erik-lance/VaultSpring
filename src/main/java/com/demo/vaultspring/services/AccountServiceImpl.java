package com.demo.vaultspring.services;

import com.demo.vaultspring.model.Account;
import com.demo.vaultspring.repositories.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;

    public AccountServiceImpl(
            AccountRepository accountRepository
    ) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> findAccountById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Account deposit(Long accountId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.valueOf(0)) <= 0) {
            // If depositing 0 or less, throw
            throw new RuntimeException("Deposit amount must be greater than zero");
        }

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));

        BigDecimal newBalance = account.getBalance().add(amount);
        account.setBalance(newBalance);

        return accountRepository.save(account);
    }

    @Override
    @Transactional
    public Account withdraw(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));

        BigDecimal newBalance = account.getBalance().subtract(amount);

        if (newBalance.compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new RuntimeException("Insufficient balance");
        } else {
            account.setBalance(newBalance);
        }

        return accountRepository.save(account);
    }
}
