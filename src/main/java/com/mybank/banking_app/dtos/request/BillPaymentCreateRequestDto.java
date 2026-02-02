package com.mybank.banking_app.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillPaymentCreateRequestDto {

    @NotNull(message = "Transaction ID is required")
    private Long transactionId;

    @NotNull(message = "Bill Type is required")
    private String billType;

    @NotNull(message = "Bill number is required")
    private String billNumber;
}
