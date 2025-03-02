package com.demo.vaultspring.service;

import com.demo.vaultspring.model.User;
import java.util.Optional;


public interface UserService {
    User createUser(User user);
    Optional<User> getUserById(Long id);
    void deleteById(Long id);
}
