package com.demo.vaultspring.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private BigDecimal balance;

    // Many bank accounts can belong to one user.
    @ManyToOne
    private User user;
}
