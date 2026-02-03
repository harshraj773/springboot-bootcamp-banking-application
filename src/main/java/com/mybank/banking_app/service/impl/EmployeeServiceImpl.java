package com.mybank.banking_app.service.impl;

import com.mybank.banking_app.dtos.request.EmployeeCreateRequestDto;
import com.mybank.banking_app.dtos.request.EmployeeUpdateRequestDto;
import com.mybank.banking_app.dtos.response.EmployeeResponseDto;
import com.mybank.banking_app.dtos.response.PageableResponseDto;
import com.mybank.banking_app.entities.Employee;
import com.mybank.banking_app.entities.Person;
import com.mybank.banking_app.enums.EmployeeStatus;
import com.mybank.banking_app.exceptions.ResourceNotFoundException;
import com.mybank.banking_app.mappers.EmployeeMapper;
import com.mybank.banking_app.repositories.EmployeeRepository;
import com.mybank.banking_app.repositories.PersonRepository;
import com.mybank.banking_app.service.EmployeeService;
import com.mybank.banking_app.utils.PaginationUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PersonRepository personRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               PersonRepository personRepository,
                               EmployeeMapper employeeMapper){
        this.employeeRepository = employeeRepository;
        this.personRepository = personRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public EmployeeResponseDto createEmployee(EmployeeCreateRequestDto dto) {
        Person person = personRepository.findById(dto.getPersonId())
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with id: "+dto.getPersonId()));
        Employee employee = employeeMapper.toEntity(dto);
        employee.setPerson(person);
        employee.setEmployeeStatus(EmployeeStatus.ACTIVE);
        Employee savedEmployee = employeeRepository.save(employee);

        return employeeMapper.toResponseDto(savedEmployee);
    }

    @Override
    @CacheEvict(
            value = "employee",
            key = "'id:' + #employeeId"
    )
    public EmployeeResponseDto updateEmployee(Long employeeId, EmployeeUpdateRequestDto dto) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: "+ employeeId));
        employeeMapper.updateEntityFromDto(dto, employee);
        Employee updatedEmployee = employeeRepository.save(employee);
        return employeeMapper.toResponseDto(updatedEmployee);
    }

    @Override
    @Cacheable(
            value = "employee",
            key = "'id:' + #employeeId"
    )
    public EmployeeResponseDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: "+ employeeId));

        return employeeMapper.toResponseDto(employee);
    }

    @Override
    public PageableResponseDto<EmployeeResponseDto> getAllEmployees(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        List<EmployeeResponseDto> employeeResponseDtoList = employeeMapper.toResponseList(employeePage.getContent());


        return PaginationUtil.getPageableResponse(employeePage, employeeResponseDtoList);
    }

    @Override
    @CacheEvict(
            value = "employee",
            key = "'id:' + #employeeId"
    )
    public void deactivateEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: "+ employeeId));
        employee.setEmployeeStatus(EmployeeStatus.INACTIVE);
        employeeRepository.save(employee);
    }
}
