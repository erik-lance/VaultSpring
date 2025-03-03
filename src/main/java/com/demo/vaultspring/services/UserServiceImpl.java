package com.demo.vaultspring.services;

import com.demo.vaultspring.exceptions.UserNotFoundException;
import com.demo.vaultspring.model.Account;
import com.demo.vaultspring.model.User;
import com.demo.vaultspring.repositories.AccountRepository;
import com.demo.vaultspring.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public UserServiceImpl(
            UserRepository userRepository,
            AccountRepository accountRepository
    ) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Returns an (optional) user in the database using id
     * @param id of the user
     * @return the user if found
     */
    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void addAccountToUser(Long userId, Account account) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        user.addAccount(account);
        accountRepository.save(account);    // Save newly added account
        userRepository.save(user);          // Save user to update relationship
    }

    @Override
    public void removeAccountFromUser(Long userId, Long accountId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Account account = accountRepository.findById(accountId)
                .orElseThrow(UserNotFoundException::new);

        user.removeAccount(account);                // Remove account from user
        accountRepository.deleteById(accountId);    // All accounts must have a user
    }
}
