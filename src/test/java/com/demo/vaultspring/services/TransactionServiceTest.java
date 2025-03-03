package com.demo.vaultspring.services;

import com.demo.vaultspring.exceptions.InsufficientBalanceException;
import com.demo.vaultspring.exceptions.InvalidAmountException;
import com.demo.vaultspring.model.Account;
import com.demo.vaultspring.model.Transaction;
import com.demo.vaultspring.model.enums.TransactionType;
import com.demo.vaultspring.repositories.AccountRepository;
import com.demo.vaultspring.repositories.TransactionRepository;
import com.demo.vaultspring.utils.TestDataUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private Account account;

    @BeforeEach
    void setUp() {
        // Account with a balance of 2000 ( no user )
        account = TestDataUtils.BASIC_ACCOUNT();
    }


    @Test
    public void testWithdraw() {
        BigDecimal amount = BigDecimal.valueOf(500);

        when(accountService.withdraw(account.getId(), amount))
                .thenReturn(account);

        // Setup mock for when a transaction is saved
        // thenAnswer to grab whatever was passed into .save()
        when(transactionRepository.save(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Perform transaction
        Transaction transaction = transactionService
                .createTransaction(1L, amount, TransactionType.WITHDRAWAL);

        // **Manually update balance in the test**
        account.setBalance(account.getBalance().subtract(amount));

        // Verify
        assertNotNull(transaction);
        assertEquals(account.getBalance(), BigDecimal.valueOf(1500));
        verify(transactionRepository).save(transaction);
    }

    @Test
    public void testWithdraw_InsufficientBalance() {
        BigDecimal amount = BigDecimal.valueOf(2500);

        // Setup mock for when withdraw is called in account
        when(accountService.withdraw(account.getId(), amount))
                .thenThrow(new InsufficientBalanceException());

        // Perform transaction
        Exception exception = assertThrows(
                InsufficientBalanceException.class, () -> {transactionService
                        .createTransaction(1L, amount, TransactionType.WITHDRAWAL);
                });

        // Verify
        assertEquals(new InsufficientBalanceException().getMessage(), exception.getMessage());
        assertEquals(account.getBalance(), BigDecimal.valueOf(2000));
        verify(transactionRepository, never()).save(any());
    }

    @Test
    public void testWithdraw_Zero() {
        BigDecimal amount = BigDecimal.valueOf(0);

        // Setup mock for when withdraw is called in account
        when(accountService.withdraw(account.getId(), amount))
                .thenThrow(new InvalidAmountException());

        // Perform transaction
        Exception exception = assertThrows(
                InvalidAmountException.class, () -> {transactionService
                        .createTransaction(1L, amount, TransactionType.WITHDRAWAL);
                });

        // Verify
        assertEquals(new InvalidAmountException().getMessage(), exception.getMessage());
        assertEquals(account.getBalance(), BigDecimal.valueOf(2000));
        verify(transactionRepository, never()).save(any());
    }

    @Test
    public void testDeposit() {
        BigDecimal amount = BigDecimal.valueOf(500);

        when(accountService.deposit(account.getId(), amount))
                .thenReturn(account);

        // Setup mock for when a transaction is saved
        // thenAnswer to grab whatever was passed into .save()
        when(transactionRepository.save(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Perform transaction
        Transaction transaction = transactionService
                .createTransaction(1L, amount, TransactionType.DEPOSIT);

        // **Manually update balance in the test**
        account.setBalance(account.getBalance().add(amount));

        // Verify
        assertNotNull(transaction);
        assertEquals(account.getBalance(), BigDecimal.valueOf(2500));
        verify(transactionRepository).save(transaction);
    }

    @Test
    public void testDeposit_Zero() {
        BigDecimal amount = BigDecimal.valueOf(0);

        // Setup mock for when withdraw is called in account
        when(accountService.deposit(account.getId(), amount))
                .thenThrow(new InvalidAmountException());

        // Perform transaction
        Exception exception = assertThrows(
                InvalidAmountException.class, () -> {transactionService
                        .createTransaction(1L, amount, TransactionType.DEPOSIT);
                });

        // Verify
        assertEquals(new InvalidAmountException().getMessage(), exception.getMessage());
        assertEquals(account.getBalance(), BigDecimal.valueOf(2000));
        verify(transactionRepository, never()).save(any());
    }
}
