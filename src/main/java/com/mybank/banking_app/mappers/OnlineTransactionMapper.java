package com.mybank.banking_app.mappers;

import com.mybank.banking_app.dtos.request.OnlineTransactionCreateRequestDto;
import com.mybank.banking_app.dtos.response.OnlineTransactionResponseDto;
import com.mybank.banking_app.entities.OnlineTransaction;
import com.mybank.banking_app.mappers.config.CentralMapperConfig;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = CentralMapperConfig.class)
public interface OnlineTransactionMapper {

    OnlineTransaction toEntity(OnlineTransactionCreateRequestDto dto);

    OnlineTransactionResponseDto toResponseDto(OnlineTransaction onlineTransaction);

    List<OnlineTransactionResponseDto> toResponseList(List<OnlineTransaction> onlineTransactions);
}
