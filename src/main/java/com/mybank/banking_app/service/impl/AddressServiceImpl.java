package com.mybank.banking_app.service.impl;

import com.mybank.banking_app.dtos.request.AddressCreateRequestDto;
import com.mybank.banking_app.dtos.request.AddressUpdateRequestDto;
import com.mybank.banking_app.dtos.response.AddressResponseDto;
import com.mybank.banking_app.entities.Address;
import com.mybank.banking_app.entities.Person;
import com.mybank.banking_app.exceptions.ResourceNotFoundException;
import com.mybank.banking_app.mappers.AddressMapper;
import com.mybank.banking_app.repositories.AddressRepository;
import com.mybank.banking_app.repositories.PersonRepository;
import com.mybank.banking_app.service.AddressService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;
    private final AddressMapper addressMapper;

    public AddressServiceImpl(AddressRepository addressRepository,
                              PersonRepository personRepository,
                              AddressMapper addressMapper){
        this.addressMapper = addressMapper;
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
    }

    @Override
    public AddressResponseDto createAddress(AddressCreateRequestDto dto) {
        Person person = personRepository.findById(dto.getPersonId())
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with id: " + dto.getPersonId()));
        Address address = addressMapper.toEntity(dto);
        address.setPerson(person);
        Address savedAddress = addressRepository.save(address);

        return addressMapper.toResponseDto(savedAddress);
    }

    @Override
    public AddressResponseDto getAddressById(Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: "+addressId));

        return addressMapper.toResponseDto(address);
    }

    @Override
    public List<AddressResponseDto> getAddressesByPerson(Long personId) {
        List<Address> addressList = addressRepository.findByPersonPersonId(personId);
        return addressMapper.toResponseList(addressList);
    }

    @Override
    public AddressResponseDto updateAddress(Long addressId, AddressUpdateRequestDto dto) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: "+addressId));

        addressMapper.updateEntityFromDto(dto, address);
        Address updatedAddress = addressRepository.save(address);
        return addressMapper.toResponseDto(updatedAddress);
    }

    @Override
    public void deactivateAddress(Long addressId) {
        addressRepository.deleteById(addressId);
    }
}
