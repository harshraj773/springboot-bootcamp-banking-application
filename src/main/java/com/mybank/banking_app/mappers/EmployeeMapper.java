package com.mybank.banking_app.mappers;

import com.mybank.banking_app.dtos.request.EmployeeCreateRequestDto;
import com.mybank.banking_app.dtos.request.EmployeeUpdateRequestDto;
import com.mybank.banking_app.dtos.response.EmployeeResponseDto;
import com.mybank.banking_app.entities.Employee;
import com.mybank.banking_app.mappers.config.CentralMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(config = CentralMapperConfig.class)
public interface EmployeeMapper {

    Employee toEntity(EmployeeCreateRequestDto dto);

    EmployeeResponseDto toResponseDto(Employee employee);

    List<EmployeeResponseDto> toResponseList(List<Employee> employees);

    void updateEntityFromDto(EmployeeUpdateRequestDto dto, @MappingTarget Employee employee);
}
