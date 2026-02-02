package com.mybank.banking_app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bill_payment")
public class BillPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billPaymentId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id")
    private OnlineTransaction transaction;

    @Column(name = "bill_type", nullable = false)
    private String billType;

    @Column(name = "bill_number", nullable = false)
    private String billNumber;
}
