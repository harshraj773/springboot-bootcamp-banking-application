package com.mybank.banking_app.mappers;


import com.mybank.banking_app.dtos.request.AccountCreateRequestDto;
import com.mybank.banking_app.dtos.response.AccountResponseDto;
import com.mybank.banking_app.entities.Account;
import com.mybank.banking_app.mappers.config.CentralMapperConfig;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = CentralMapperConfig.class)
public interface AccountMapper {

    Account toEntity(AccountCreateRequestDto dto);

    AccountResponseDto toResponseDto(Account account);

    List<AccountResponseDto> toResponseList(List<Account> accounts);
}

