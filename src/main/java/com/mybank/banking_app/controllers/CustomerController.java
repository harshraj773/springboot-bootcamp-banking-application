package com.mybank.banking_app.controllers;

import com.mybank.banking_app.dtos.request.CustomerCreateRequestDto;
import com.mybank.banking_app.dtos.request.CustomerUpdateRequestDto;
import com.mybank.banking_app.dtos.response.CustomerResponseDto;
import com.mybank.banking_app.dtos.response.PageableResponseDto;
import com.mybank.banking_app.utils.ApiResponse;
import com.mybank.banking_app.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // ---------------- CREATE CUSTOMER --------------------
    @PostMapping
    public ResponseEntity<ApiResponse<CustomerResponseDto>> createCustomer(
            @Valid @RequestBody CustomerCreateRequestDto dto
            ) {
        CustomerResponseDto createdResponseDto = customerService.createCustomer(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Customer created successfully", createdResponseDto));
    }

    // ---------------- GET CUSTOMER BY ID --------------------
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponseDto>> getCustomerById(@PathVariable Long id) {
        CustomerResponseDto responseDto = customerService.getCustomerById(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Customer fetched successfully", responseDto));
    }

    // ---------------- GET ALL CUSTOMERS ----------------------
    @GetMapping
    public ResponseEntity<ApiResponse<PageableResponseDto<CustomerResponseDto>>> getAllCustomers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "customerId") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        PageableResponseDto<CustomerResponseDto> customers = customerService.getAllCustomers(page, size, sortBy, sortDir);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Customers fetched successfully", customers));
    }

    // --------------- UPDATE CUSTOMER STATUS -------------------
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponseDto>> updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerUpdateRequestDto dto) {
        CustomerResponseDto updatedResponseDto = customerService.updateCustomerStatus(id, dto);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Customer status updated successfully", updatedResponseDto));
    }

    // ------------------------- DELETE -------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteCustomer(@PathVariable Long id) {
        customerService.deactivateCustomer(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Customer deleted successfully", null));
    }

}
