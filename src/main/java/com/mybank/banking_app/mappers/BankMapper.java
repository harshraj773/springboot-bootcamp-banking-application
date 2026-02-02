package com.mybank.banking_app.mappers;

import com.mybank.banking_app.dtos.request.BankCreateRequestDto;
import com.mybank.banking_app.dtos.request.BankUpdateRequestDto;
import com.mybank.banking_app.dtos.response.BankResponseDto;
import com.mybank.banking_app.entities.Bank;
import com.mybank.banking_app.mappers.config.CentralMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(config = CentralMapperConfig.class)
public interface BankMapper {

    Bank toEntity(BankCreateRequestDto dto);

    BankResponseDto toResponseDto(Bank bank);

    List<BankResponseDto> toResponseList(List<Bank> banks);

    void updateEntityFromDto(BankUpdateRequestDto dto, @MappingTarget Bank bank);
}
