package com.mybank.banking_app.dtos.response;

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
public class InsuranceResponseDto {

    private Long insuranceId;

    private String insuranceType;

    private BigDecimal insuranceAmount;

    private LocalDate validity;

    private CustomerResponseDto customer;
}
