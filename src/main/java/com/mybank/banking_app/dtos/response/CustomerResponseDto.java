package com.mybank.banking_app.dtos.response;

import com.mybank.banking_app.entities.*;
import com.mybank.banking_app.enums.CustomerStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CustomerResponseDto {

    private Long customerId;

    private PersonResponseDto person;

    private CustomerStatus customerStatus;

    /*

    private List<AccountResponseDto> accounts;

    private List<LoanResponseDto> loans;

    private List<InsuranceResponseDto> insurances;

    private List<LockerResponseDto> lockers;

    private List<CreditCardResponseDto> creditCards;

     */
}
