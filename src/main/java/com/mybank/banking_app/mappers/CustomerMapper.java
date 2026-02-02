package com.mybank.banking_app.mappers;

import com.mybank.banking_app.dtos.request.CustomerCreateRequestDto;
import com.mybank.banking_app.dtos.response.CustomerResponseDto;
import com.mybank.banking_app.entities.Customer;
import com.mybank.banking_app.mappers.config.CentralMapperConfig;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = CentralMapperConfig.class)
public interface CustomerMapper {

    Customer toEntity(CustomerCreateRequestDto dto);

    CustomerResponseDto toResponseDto(Customer customer);

    List<CustomerResponseDto> toResponseList(List<Customer> customers);
}
