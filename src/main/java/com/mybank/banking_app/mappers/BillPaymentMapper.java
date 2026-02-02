package com.mybank.banking_app.mappers;

import com.mybank.banking_app.dtos.request.BillPaymentCreateRequestDto;
import com.mybank.banking_app.dtos.response.BillPaymentResponseDto;
import com.mybank.banking_app.entities.BillPayment;
import com.mybank.banking_app.mappers.config.CentralMapperConfig;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = CentralMapperConfig.class)
public interface BillPaymentMapper {

    BillPayment toEntity(BillPaymentCreateRequestDto dto);

    BillPaymentResponseDto toResponseDto(BillPayment billPayment);

    List<BillPaymentResponseDto> toResponseList(List<BillPayment> billPayments);
}
