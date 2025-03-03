package com.demo.vaultspring.model;

import com.demo.vaultspring.model.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    private BigDecimal amount;

    // If deposit, withdrawal, or other.
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private LocalDateTime timestamp;

    @ManyToOne
    private Account account;

    /**
     * Constructor with no ID (It is automatically generated)
     * @param amount Amount being transacted
     * @param type If withdrawal or depositc, etc.
     * @param timestamp Time of transaction
     * @param account Account involved in transaction
     */
    public Transaction(
            BigDecimal amount, TransactionType type, LocalDateTime timestamp, Account account
    ) {
        this.amount = amount;
        this.type = type;
        this.timestamp = timestamp;
        this.account = account;
    }

    /**
     * Constructor for no set time
     * @param id Transaction ID
     * @param amount Amount being transacted
     * @param type If withdrawal or deposit, etc.
     * @param account Account involved in transaction
     */
    public Transaction(
            Long id, BigDecimal amount, TransactionType type, Account account
    ) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.timestamp = LocalDateTime.now();
        this.account = account;
    }
}
