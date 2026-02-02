package com.mybank.banking_app.service;

import com.mybank.banking_app.dtos.request.PersonCreateRequestDto;
import com.mybank.banking_app.dtos.request.PersonUpdateRequestDto;
import com.mybank.banking_app.dtos.response.PageableResponseDto;
import com.mybank.banking_app.dtos.response.PersonResponseDto;

public interface PersonService {

    PersonResponseDto createPerson(PersonCreateRequestDto dto);

    PersonResponseDto updatePerson(Long personId, PersonUpdateRequestDto dto);

    PersonResponseDto getPersonById(Long personId);

    PageableResponseDto<PersonResponseDto> getAllPersons(int page, int size, String sortBy, String sortDir);

    void deactivatePerson(Long personId);
}
