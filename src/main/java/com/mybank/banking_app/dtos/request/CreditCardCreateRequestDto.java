package com.mybank.banking_app.dtos.request;

import com.mybank.banking_app.enums.CreditCardStatus;
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
public class CreditCardCreateRequestDto {

    @NotBlank(message = "Credit card is required")
    @Size(min = 16, max = 16, message = "Card number must be 16 digits")
    @Pattern(regexp="^\\d{16}$", message = "Card number must contain only digits")
    private String cardNumber;

    @NotNull(message = "Card limit cannot be null")
    @Positive(message = "Card limit must be positive")
    private BigDecimal creditLimit;

    @NotNull(message = "Available limit cannot be null")
    @Positive(message = "Available limit must be positive")
    private BigDecimal availableLimit;

    @NotNull(message = "Card status is required")
    private CreditCardStatus status;

    @NotBlank(message = "Expiry is required")
    @Pattern(regexp="^(0[1-9]|1[0-2])/[0-9]{2}$", message = "Expiry must be in MM/YY format")
    private LocalDate expiryDate;

    @NotNull(message = "Customer ID is required")
    private Long customerId;
}
