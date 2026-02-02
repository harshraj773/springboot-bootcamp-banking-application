package com.mybank.banking_app.service;

import com.mybank.banking_app.dtos.request.CustomerCreateRequestDto;
import com.mybank.banking_app.dtos.request.CustomerUpdateRequestDto;
import com.mybank.banking_app.dtos.response.CustomerResponseDto;
import com.mybank.banking_app.dtos.response.PageableResponseDto;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    CustomerResponseDto createCustomer(CustomerCreateRequestDto dto);

    CustomerResponseDto updateCustomerStatus(Long customerId, CustomerUpdateRequestDto dto);

    CustomerResponseDto getCustomerById(Long customerId);

    PageableResponseDto<CustomerResponseDto> getAllCustomers(int page, int size, String sortBy, String sortDir);

    void deactivateCustomer(Long customerId); // internally we will update the status as INACTIVE
}
