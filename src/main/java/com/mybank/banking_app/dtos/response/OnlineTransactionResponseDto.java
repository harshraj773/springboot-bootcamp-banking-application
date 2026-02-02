package com.mybank.banking_app.dtos.response;

import com.mybank.banking_app.entities.Account;
import com.mybank.banking_app.entities.BillPayment;
import com.mybank.banking_app.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OnlineTransactionResponseDto {
    private Long transactionId;

    private AccountResponseDto fromAccount;

    private AccountResponseDto toAccount;

    private BigDecimal amount;

    private TransactionStatus status;

    private BillPaymentResponseDto billPayment;
}
