package com.mybank.banking_app.entities;

import com.mybank.banking_app.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "online_transaction")
public class OnlineTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @JoinColumn(name = "from_account_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Account fromAccount;

    @JoinColumn(name = "to_account_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Account toAccount;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_status")
    private TransactionStatus status;

    @OneToOne(mappedBy = "transaction", fetch = FetchType.LAZY)
    private BillPayment billPayment;
}
