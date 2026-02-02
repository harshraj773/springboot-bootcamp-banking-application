package com.mybank.banking_app.mappers;

import com.mybank.banking_app.dtos.request.InsuranceCreateRequestDto;
import com.mybank.banking_app.dtos.response.InsuranceResponseDto;
import com.mybank.banking_app.entities.Insurance;
import com.mybank.banking_app.mappers.config.CentralMapperConfig;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = CentralMapperConfig.class)
public interface InsuranceMapper {

    Insurance toEntity(InsuranceCreateRequestDto dto);

    InsuranceResponseDto toResponseDto(Insurance insurance);

    List<InsuranceResponseDto> toResponseList(List<Insurance> insurances);
}
