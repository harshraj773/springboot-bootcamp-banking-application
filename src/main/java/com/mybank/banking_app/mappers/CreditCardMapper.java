package com.mybank.banking_app.mappers;

import com.mybank.banking_app.dtos.request.CreditCardCreateRequestDto;
import com.mybank.banking_app.dtos.response.CreditCardResponseDto;
import com.mybank.banking_app.entities.CreditCard;
import com.mybank.banking_app.mappers.config.CentralMapperConfig;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = CentralMapperConfig.class)
public interface CreditCardMapper {

    CreditCard toEntity(CreditCardCreateRequestDto dto);

    CreditCardResponseDto toResponseDto(CreditCard creditCard);

    List<CreditCardResponseDto> toResponseList(List<CreditCard> creditCards);
}
