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
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
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
    // @Retry(name = "personServiceRetry", fallbackMethod = "createPersonFallBack")
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
    @CachePut(
            value = "person",
            key = "'id:' + #personId"
    )
    public PersonResponseDto updatePerson(Long personId, PersonUpdateRequestDto dto) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with id: "+ personId));
        personMapper.updateEntityFromDto(dto, person);
        Person updatedPerson = personRepository.save(person);

        return personMapper.toResponseDto(updatedPerson);
    }

    @Override
    @Cacheable(
            value = "person",
            key = "'id:' + #personId"
    )
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
    @CacheEvict(
            value = "person",
            key = "'id:' + #personId"
    )
    public void deactivatePerson(Long personId) {
        personRepository.deleteById(personId);
    }

    /*
    public PersonResponseDto createPersonFallBack(PersonCreateRequestDto dto, Throwable ex) {
        throw new BusinessException(
                "Database temporarily unavailable. Please retry later");
    }
     */

}
