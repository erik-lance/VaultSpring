package com.demo.vaultspring.repository;

import com.demo.vaultspring.model.Account;
import com.demo.vaultspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUser(User user);
}
