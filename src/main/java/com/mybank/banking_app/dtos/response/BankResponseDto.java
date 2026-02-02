package com.mybank.banking_app.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankResponseDto {
    private Long bankId;

    private String bankName;

    private String bankCode;

    private String headquater;
}
