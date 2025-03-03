package com.demo.vaultspring.services;

import com.demo.vaultspring.exceptions.AccountNotFoundException;
import com.demo.vaultspring.model.Account;
import com.demo.vaultspring.model.Transaction;
import com.demo.vaultspring.model.enums.TransactionType;
import com.demo.vaultspring.repositories.AccountRepository;
import com.demo.vaultspring.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService{
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    @Override
    @Transactional
    public Transaction createTransaction(Long accountId, BigDecimal amount, TransactionType type) {
        // Checking account's existence is done during deposit/withdraw
        Account account = null;

        // Calculate transaction
        if (type == TransactionType.DEPOSIT) {
            account = accountService.deposit(accountId, amount);
        }
        else if (type == TransactionType.WITHDRAWAL) {
            account = accountService.withdraw(accountId, amount);
        }

        if (account == null) {
            throw new AccountNotFoundException();
        }

        Transaction transaction = new Transaction(
                amount, type, LocalDateTime.now(), account
        );

        // Update balance before saving
        return transactionRepository.save(transaction);
    }

    @Override
    public Optional<Transaction> findTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}
