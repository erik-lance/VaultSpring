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
     * Constructor for no set time
     * @param id Transaction ID
     * @param amount Amount being transacted
     * @param type If withdrawal or deposit, etc.
     * @param account User doing the transaction
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
