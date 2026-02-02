package com.mybank.banking_app.service;

import com.mybank.banking_app.dtos.request.BankCreateRequestDto;
import com.mybank.banking_app.dtos.request.BankUpdateRequestDto;
import com.mybank.banking_app.dtos.response.BankResponseDto;

import java.util.List;

public interface BankService {

    BankResponseDto createBank(BankCreateRequestDto dto);

    BankResponseDto updateBank(Long bankId, BankUpdateRequestDto dto);
}
