package com.demo.vaultspring.service;

import com.demo.vaultspring.model.Account;
import com.demo.vaultspring.model.User;
import com.demo.vaultspring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
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

    /**
     * Adds account to a user
     * @param account the account to be added
     * @param user the user to own the bank account
     */
    @Override
    public void addAccount(Account account, User user) {
        // TODO: Implement feature in Account
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
