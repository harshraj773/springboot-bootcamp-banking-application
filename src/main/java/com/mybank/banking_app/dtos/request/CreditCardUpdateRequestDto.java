package com.mybank.banking_app.dtos.request;

import com.mybank.banking_app.enums.CreditCardStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardUpdateRequestDto {

    @NotNull(message = "Available limit cannot be null")
    @Positive(message = "Available limit must be positive")
    private BigDecimal availableLimit;

    @NotNull(message = "Card status is required")
    private CreditCardStatus status;
}
