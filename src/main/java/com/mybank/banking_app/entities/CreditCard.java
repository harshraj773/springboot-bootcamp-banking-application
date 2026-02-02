package com.mybank.banking_app.entities;

import com.mybank.banking_app.enums.CreditCardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @Column(name = "card_number", nullable = false, unique = true, length = 16)
    private String cardNumber;

    @Column(name = "card_limit", nullable = false)
    private BigDecimal cardLimit;

    @Column(name = "available_limit", nullable = false)
    private BigDecimal availableLimit;

    @Column(name = "card_expiry", nullable = false)
    private LocalDate expiryDate;

    @Column(name = "card_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private CreditCardStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}
