package com.mybank.banking_app.service.impl;

import com.mybank.banking_app.dtos.request.BankCreateRequestDto;
import com.mybank.banking_app.dtos.request.BankUpdateRequestDto;
import com.mybank.banking_app.dtos.response.BankResponseDto;
import com.mybank.banking_app.entities.Bank;
import com.mybank.banking_app.exceptions.ResourceNotFoundException;
import com.mybank.banking_app.mappers.BankMapper;
import com.mybank.banking_app.repositories.BankRepository;
import com.mybank.banking_app.service.BankService;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;
    private final BankMapper bankMapper;

    public BankServiceImpl(BankRepository bankRepository,
                           BankMapper bankMapper){
        this.bankRepository = bankRepository;
        this.bankMapper = bankMapper;
    }

    @Override
    public BankResponseDto createBank(BankCreateRequestDto dto) {
        Bank savedBank = bankRepository.save(bankMapper.toEntity(dto));
        return bankMapper.toResponseDto(savedBank);
    }

    @Override
    public BankResponseDto updateBank(Long bankId, BankUpdateRequestDto dto) {
        Bank bank  = bankRepository.findById(bankId)
                .orElseThrow(() -> new ResourceNotFoundException("Bank not found with id: "+ bankId));

        bankMapper.updateEntityFromDto(dto, bank);
        Bank updatedBank = bankRepository.save(bank);
        return bankMapper.toResponseDto(updatedBank);
    }
}
