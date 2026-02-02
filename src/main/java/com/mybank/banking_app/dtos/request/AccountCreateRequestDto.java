package com.mybank.banking_app.dtos.request;
import com.mybank.banking_app.enums.AccountType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreateRequestDto {

    @NotBlank(message = "Account number must not be blank")
    @Size(min = 8, max = 20, message = "Account number must be between 8 and 20 characters")
    private String accountNumber;

    @NotNull(message = "Account type is required")
    private AccountType accountType;

    @NotNull(message = "Balance is required")
    @PositiveOrZero(message = "Balance must be zero or positive")
    private BigDecimal balance;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Branch ID is required")
    private Long branchId;
}


