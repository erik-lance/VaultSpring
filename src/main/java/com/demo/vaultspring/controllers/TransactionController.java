package com.demo.vaultspring.controllers;

import com.demo.vaultspring.model.Transaction;
import com.demo.vaultspring.model.enums.TransactionType;
import com.demo.vaultspring.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/{accountId}")
    public ResponseEntity<Transaction> createTransaction(
            @PathVariable Long accountId,
            @RequestParam BigDecimal amount,
            @RequestParam TransactionType type
            ) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            // Can't withdraw nor deposit with said amount
            return ResponseEntity.badRequest().body(null);
        }

        try {
            Transaction transaction = transactionService.createTransaction(accountId, amount, type);
            return ResponseEntity.ok(transaction);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        return  transactionService.findTransactionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return  ResponseEntity.noContent().build();
    }

}
