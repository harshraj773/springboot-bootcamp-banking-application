package com.mybank.banking_app.dtos.response;
import com.mybank.banking_app.enums.CreditCardStatus;
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
public class CreditCardResponseDto {

    private Long cardId;

    private String cardNumber;

    private BigDecimal cardLimit;

    private BigDecimal availableLimit;

    private LocalDate expiryDate;

    private CreditCardStatus status;

    private CustomerResponseDto customer;
}
