package com.mybank.banking_app.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanResponseDto {

    private Long loanId;

    private String loanNumber;

    private BigDecimal loanAmount;

    private CustomerResponseDto customer;
}
