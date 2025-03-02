package com.demo.vaultspring.controllers;

import com.demo.vaultspring.model.Account;
import com.demo.vaultspring.model.User;
import com.demo.vaultspring.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/accounts")
    public ResponseEntity<Account> addAccountToUser(@PathVariable Long userId, @RequestBody Account account) {
        userService.addAccountToUser(userId, account);  // Account sets their user variable automatically (see User.java)
        return ResponseEntity.ok(account);
    }

    @DeleteMapping("/{userId}/accounts/{accountId}")
    public ResponseEntity<Void> removeAccountFromUser(@PathVariable Long userId, @PathVariable Long accountId) {
        userService.removeAccountFromUser(userId, accountId); // Account deleted automatically (see User.java)
        return ResponseEntity.noContent().build();
    }
}
