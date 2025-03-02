package com.demo.vaultspring.services;

import com.demo.vaultspring.model.Account;
import com.demo.vaultspring.repositories.AccountRepository;
import com.demo.vaultspring.utils.TestDataUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    private Account account;

    @BeforeEach
    void setUp() {
        account = TestDataUtils.BASIC_ACCOUNT();    // No user set
    }

    @Test
    public void testFindAccountById_WhenAccountExists() {
        when(accountRepository.findById(1L))
                .thenReturn(Optional.of(account));

        Optional<Account> foundAccount = accountService.findAccountById(1L);
        assertTrue(foundAccount.isPresent());
        assertEquals(
                account.getAccountNumber(),
                foundAccount.get().getAccountNumber()
        );
    }

    @Test
    public void testFindAccountById_NotFound() {
        when(accountRepository.findById(2L))
                .thenReturn(Optional.empty());

        Optional<Account> foundAccount = accountService.findAccountById(2L);
        assertFalse(foundAccount.isPresent());
    }

    @Test
    public void testCreateAccount() {
        when(accountRepository.save(any(Account.class)))
                .thenReturn(account);

        Account savedAccount = accountService.createAccount(account);
        assertNotNull(savedAccount);
        assertEquals(
                account.getAccountNumber(),
                savedAccount.getAccountNumber()
        );
    }

    @Test
    public void testDeleteAccount() {
        doNothing().when(accountRepository).deleteById(1L);
        accountService.deleteAccount(1L);
        verify(accountRepository, times(1)).deleteById(1L);
    }
}
