package com.mybank.banking_app.service.impl;

import com.mybank.banking_app.dtos.request.PersonCreateRequestDto;
import com.mybank.banking_app.dtos.request.PersonUpdateRequestDto;
import com.mybank.banking_app.dtos.response.PageableResponseDto;
import com.mybank.banking_app.dtos.response.PersonResponseDto;
import com.mybank.banking_app.entities.Person;
import com.mybank.banking_app.exceptions.BusinessException;
import com.mybank.banking_app.exceptions.ResourceNotFoundException;
import com.mybank.banking_app.mappers.PersonMapper;
import com.mybank.banking_app.repositories.PersonRepository;
import com.mybank.banking_app.service.PersonService;
import com.mybank.banking_app.utils.PaginationUtil;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import java.sql.SQLTransientConnectionException;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonServiceImpl(PersonRepository personRepository,
                             PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }
    @Override
    @Retryable(
            retryFor = {RuntimeException.class, CannotCreateTransactionException.class,
                    DataAccessResourceFailureException.class,
                    SQLTransientConnectionException.class,
                    JDBCConnectionException.class
            },
            maxAttempts = 5,
            backoff = @Backoff(delay = 2000, multiplier = 4)
    )
    public PersonResponseDto createPerson(PersonCreateRequestDto dto) {

        if(personRepository.existsByEmail(dto.getEmail()))
            throw new BusinessException("Email already exists");

        if(personRepository.existsByContactNumber(dto.getContactNumber()))
            throw new BusinessException(("Contact number already exists"));

        Person person = personMapper.toEntity(dto);
        Person savedPerson = personRepository.save(person);
        return personMapper.toResponseDto(savedPerson);
    }

    @Override
    public PersonResponseDto updatePerson(Long personId, PersonUpdateRequestDto dto) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with id: "+ personId));
        personMapper.updateEntityFromDto(dto, person);
        Person updatedPerson = personRepository.save(person);

        return personMapper.toResponseDto(updatedPerson);
    }

    @Override
    public PersonResponseDto getPersonById(Long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with id: "+ personId));
        return personMapper.toResponseDto(person);
    }

    @Override
    public PageableResponseDto<PersonResponseDto> getAllPersons(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Person> personPage = personRepository.findAll(pageable);
        List<PersonResponseDto> personResponseDtoList = personMapper.toResponseList(personPage.getContent());

        return PaginationUtil.getPageableResponse(personPage, personResponseDtoList);
    }

    @Override
    public void deactivatePerson(Long personId) {
        personRepository.deleteById(personId);
    }

    @Recover
    public String recover(RuntimeException ex) {
        return "Fallback response from @Recover";
    }
}
