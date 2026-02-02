package com.mybank.banking_app.dtos.response;
import com.mybank.banking_app.entities.OnlineTransaction;
import com.mybank.banking_app.enums.AccountStatus;
import com.mybank.banking_app.enums.AccountType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDto {

    private Long accountId;

    private String accountNumber;

    private AccountType accountType;

    private BigDecimal balance;

    private AccountStatus status;

    private LocalDate openedAt;

    private CustomerResponseDto customer;

    private BranchResponseDto branch;
}
