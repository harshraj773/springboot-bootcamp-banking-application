package com.mybank.banking_app.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class InsuranceCreateRequestDto {

    @NotBlank(message = "Insurance type is required")
    private String insuranceType;

    @NotNull(message = "Insurance amount is required")
    @Positive(message = "Insurance amount must be positive")
    private BigDecimal insuranceAmount;

    @NotNull(message = "Validity date is required")
    private LocalDate validity;

    @NotNull(message = "Customer ID is required")
    private Long customerId;
}

