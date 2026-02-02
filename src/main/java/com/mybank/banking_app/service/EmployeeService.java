package com.mybank.banking_app.service;

import com.mybank.banking_app.dtos.request.EmployeeCreateRequestDto;
import com.mybank.banking_app.dtos.request.EmployeeUpdateRequestDto;
import com.mybank.banking_app.dtos.response.EmployeeResponseDto;
import com.mybank.banking_app.dtos.response.PageableResponseDto;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDto createEmployee(EmployeeCreateRequestDto dto);

    EmployeeResponseDto updateEmployee(Long employeeId, EmployeeUpdateRequestDto dto);

    EmployeeResponseDto getEmployeeById(Long employeeId);

    PageableResponseDto<EmployeeResponseDto> getAllEmployees(int page, int size, String sortBy, String sortDir);

    void deactivateEmployee(Long employeeId); // internally status -> INACTIVE
}
