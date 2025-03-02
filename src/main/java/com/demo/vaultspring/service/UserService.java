package com.demo.vaultspring.service;

import com.demo.vaultspring.model.Account;
import com.demo.vaultspring.model.User;
import com.demo.vaultspring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {
    User createUser(User user);
    Optional<User> getUserById(Long id);
    void deleteById(Long id);
}
