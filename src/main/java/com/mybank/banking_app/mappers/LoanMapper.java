package com.mybank.banking_app.mappers;

import com.mybank.banking_app.dtos.request.LoanCreateRequestDto;
import com.mybank.banking_app.dtos.response.LoanResponseDto;
import com.mybank.banking_app.entities.Loan;
import com.mybank.banking_app.mappers.config.CentralMapperConfig;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = CentralMapperConfig.class)
public interface LoanMapper {

    Loan toEntity(LoanCreateRequestDto dto);

    LoanResponseDto toResponseDto(Loan loan);

    List<LoanResponseDto> toResponseList(List<Loan> loans);
}

