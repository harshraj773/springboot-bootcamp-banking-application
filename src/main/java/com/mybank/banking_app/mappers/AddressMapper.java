package com.mybank.banking_app.mappers;

import com.mybank.banking_app.dtos.request.AddressCreateRequestDto;
import com.mybank.banking_app.dtos.request.AddressUpdateRequestDto;
import com.mybank.banking_app.dtos.response.AddressResponseDto;
import com.mybank.banking_app.entities.Address;
import com.mybank.banking_app.mappers.config.CentralMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(config = CentralMapperConfig.class)
public interface AddressMapper {

    Address toEntity(AddressCreateRequestDto dto);

    AddressResponseDto toResponseDto(Address address);

    void updateEntityFromDto(AddressUpdateRequestDto dto, @MappingTarget Address address);

    List<AddressResponseDto> toResponseList(List<Address> addresses);
}
