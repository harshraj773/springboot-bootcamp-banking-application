package com.mybank.banking_app.service;

import com.mybank.banking_app.dtos.request.AddressCreateRequestDto;
import com.mybank.banking_app.dtos.request.AddressUpdateRequestDto;
import com.mybank.banking_app.dtos.response.AddressResponseDto;

import java.util.List;

public interface AddressService {

    AddressResponseDto createAddress(AddressCreateRequestDto dto);

    AddressResponseDto getAddressById(Long addressId);

    List<AddressResponseDto> getAddressesByPerson(Long personId);

    AddressResponseDto updateAddress(Long addressId, AddressUpdateRequestDto dto);

    void deactivateAddress(Long addressId);
}
