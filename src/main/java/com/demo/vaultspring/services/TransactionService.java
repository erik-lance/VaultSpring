package com.demo.vaultspring.services;

import com.demo.vaultspring.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction);
    Optional<Transaction> findTransactionById(Long id);
    List<Transaction> getAllTransactions();
    void deleteTransaction(Long id);
}
