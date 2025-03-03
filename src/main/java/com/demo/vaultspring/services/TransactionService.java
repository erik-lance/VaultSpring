package com.demo.vaultspring.services;

import com.demo.vaultspring.model.Transaction;
import com.demo.vaultspring.model.enums.TransactionType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TransactionService {
    Transaction createTransaction(Long accountId, BigDecimal amount, TransactionType type);
    Optional<Transaction> findTransactionById(Long id);
    List<Transaction> getAllTransactions();
    void deleteTransaction(Long id);
}
