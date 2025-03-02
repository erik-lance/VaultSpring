package com.demo.vaultspring.services;

import com.demo.vaultspring.model.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    User createUser(User user);
    Optional<User> getUserById(Long id);
    List<User> getAllUsers();
    void deleteUser(Long id);
}
