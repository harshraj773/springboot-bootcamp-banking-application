package com.mybank.banking_app.entities;

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
@Table(name = "insurance")
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long insuranceId;

    @Column(name = "insurance_type", nullable = false)
    private String insuranceType;

    @Column(name = "insurance_amount", nullable = false)
    private BigDecimal insuranceAmount;

    @Column(name = "loan_validity", nullable = false)
    private LocalDate validity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
