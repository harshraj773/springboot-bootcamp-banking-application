package com.mybank.banking_app.service.impl;

import com.mybank.banking_app.dtos.request.CustomerCreateRequestDto;
import com.mybank.banking_app.dtos.request.CustomerUpdateRequestDto;
import com.mybank.banking_app.dtos.response.CustomerResponseDto;
import com.mybank.banking_app.dtos.response.PageableResponseDto;
import com.mybank.banking_app.entities.Customer;
import com.mybank.banking_app.entities.Person;
import com.mybank.banking_app.enums.CustomerStatus;
import com.mybank.banking_app.exceptions.ResourceNotFoundException;
import com.mybank.banking_app.mappers.CustomerMapper;
import com.mybank.banking_app.repositories.CustomerRepository;
import com.mybank.banking_app.repositories.PersonRepository;
import com.mybank.banking_app.service.CustomerService;
import com.mybank.banking_app.utils.PaginationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PersonRepository personRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository,
                               PersonRepository personRepository,
                               CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
        this.personRepository = personRepository;
    }
    @Override
    public CustomerResponseDto createCustomer(CustomerCreateRequestDto dto) {
        Person person = personRepository.findById(dto.getPersonId())
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with id: "+ dto.getPersonId()));

        Customer customer = customerMapper.toEntity(dto);
        customer.setCustomerStatus(CustomerStatus.ACTIVE);
        customer.setPerson(person);
        Customer savedCustomer = customerRepository.save(customer);

        return customerMapper.toResponseDto(savedCustomer);
    }

    @Override
    public CustomerResponseDto updateCustomerStatus(Long customerId, CustomerUpdateRequestDto dto) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: "+ customerId));
        customer.setCustomerStatus(dto.getCustomerStatus());
        Customer updatedCustomer = customerRepository.save(customer);

        return customerMapper.toResponseDto(updatedCustomer);
    }

    @Override
    public CustomerResponseDto getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: "+ customerId));
        if(customer.getCustomerStatus() == CustomerStatus.INACTIVE)
            throw new ResourceNotFoundException("Branch Inactive");
        return customerMapper.toResponseDto(customer);
    }

    @Override
    public PageableResponseDto<CustomerResponseDto> getAllCustomers(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Customer> customerPage = customerRepository.findAll(pageable);
        List<CustomerResponseDto> customerResponseDtoList = customerMapper.toResponseList(customerPage.getContent());

        return PaginationUtil.getPageableResponse(customerPage, customerResponseDtoList);
    }

    @Override
    public void deactivateCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: "+ customerId));
        customer.setCustomerStatus(CustomerStatus.CLOSED);
        customerRepository.save(customer);
    }
}
