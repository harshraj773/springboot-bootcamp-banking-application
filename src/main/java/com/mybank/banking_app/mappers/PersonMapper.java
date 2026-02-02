package com.mybank.banking_app.mappers;

import com.mybank.banking_app.dtos.request.PersonCreateRequestDto;
import com.mybank.banking_app.dtos.request.PersonUpdateRequestDto;
import com.mybank.banking_app.dtos.response.PersonResponseDto;
import com.mybank.banking_app.entities.Person;
import com.mybank.banking_app.mappers.config.CentralMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(config = CentralMapperConfig.class)
public interface PersonMapper {

    Person toEntity(PersonCreateRequestDto dto);

    PersonResponseDto toResponseDto(Person person);

    void updateEntityFromDto(PersonUpdateRequestDto dto, @MappingTarget Person person);

    List<PersonResponseDto> toResponseList(List<Person> persons);
}
