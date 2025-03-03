package com.demo.vaultspring.services;

import com.demo.vaultspring.model.Account;
import com.demo.vaultspring.model.User;
import com.demo.vaultspring.repositories.AccountRepository;
import com.demo.vaultspring.repositories.UserRepository;
import com.demo.vaultspring.utils.TestDataUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @InjectMocks
    private AccountServiceImpl accountService;

    private User user;

    @BeforeEach
    void setUp() {
        user = TestDataUtils.USER_WITH_1_ACCOUNT();
    }

    @Test
    public void testFindUserById_WhenUserExists() {
        Long ID_TO_FIND = 1L;

        when(userRepository.findById(ID_TO_FIND))
                .thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.getUserById(ID_TO_FIND);
        assertTrue(foundUser.isPresent());
        assertEquals(
                user.getUsername(),
                foundUser.get().getUsername()
        );
    }

    @Test
    public void testFindUserById_NotFound() {
        Long ID_TO_FIND = 2L;

        when(userRepository.findById(ID_TO_FIND))
                .thenReturn(Optional.empty());

        Optional<User> foundUser = userService.getUserById(ID_TO_FIND);
        assertFalse(foundUser.isPresent());
    }

    @Test
    public void testCreateUser() {
        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        User savedUser = userService.createUser(user);
        assertNotNull(savedUser);
        assertEquals(
                user.getUsername(),
                savedUser.getUsername()
        );
    }

    @Test
    public void testDeleteUser() {
        Long USER_ID = 1L;
        Long ACCOUNT_ID = user.getAccounts().getFirst().getId();

        // Delete User
        doNothing().when(userRepository).deleteById(USER_ID);
        userService.deleteUser(USER_ID);

        verify(userRepository, times(1)).deleteById(USER_ID);

        // Verify account was deleted
        when(accountRepository.findById(ACCOUNT_ID))
                .thenReturn(Optional.empty());

        Optional<Account> foundAccount = accountService.findAccountById(ACCOUNT_ID);
        assertFalse(foundAccount.isPresent());
    }

    @Test
    public void testDeleteUser_WithMultipleAccounts() {
        user = TestDataUtils.USER_WITH_3_ACCOUNTS();

        Long USER_ID = 1L;
        Long[] ACCOUNT_ID = {
                user.getAccounts().get(0).getId(),
                user.getAccounts().get(1).getId(),
                user.getAccounts().get(2).getId(),
        };

        // Delete User
        doNothing().when(userRepository).deleteById(USER_ID);
        userService.deleteUser(USER_ID);

        verify(userRepository, times(1)).deleteById(USER_ID);

        // Verify account was deleted
        when(accountRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        for (Long aLong : ACCOUNT_ID) {
            Optional<Account> foundAccount = accountService.findAccountById(aLong);
            assertFalse(foundAccount.isPresent());
        }
    }
}
