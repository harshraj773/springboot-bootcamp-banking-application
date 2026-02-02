package com.mybank.banking_app.controllers;

import com.mybank.banking_app.dtos.request.EmployeeCreateRequestDto;
import com.mybank.banking_app.dtos.request.EmployeeUpdateRequestDto;
import com.mybank.banking_app.dtos.response.EmployeeResponseDto;
import com.mybank.banking_app.dtos.response.PageableResponseDto;
import com.mybank.banking_app.utils.ApiResponse;
import com.mybank.banking_app.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // -------------------- CREATE EMPLOYEE --------------------

    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeResponseDto>> createEmployee(
            @Valid @RequestBody EmployeeCreateRequestDto dto) {

        EmployeeResponseDto createdEmployeeDto = employeeService.createEmployee(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Employee created successfully", createdEmployeeDto));
    }

    // -------------------- GET EMPLOYEE BY ID --------------------

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponseDto>> getEmployeeById(
            @PathVariable Long id) {

        EmployeeResponseDto employee = employeeService.getEmployeeById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Employee fetched successfully", employee)
        );
    }

    // -------------------- GET ALL EMPLOYEES --------------------

    @GetMapping
    public ResponseEntity<ApiResponse<PageableResponseDto<EmployeeResponseDto>>> getAllEmployees(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "employeeId") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {

        PageableResponseDto<EmployeeResponseDto> employees = employeeService.getAllEmployees(page, size, sortBy, sortDir);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Employees fetched successfully", employees)
        );
    }

    // -------------------- UPDATE EMPLOYEE --------------------

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponseDto>> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeUpdateRequestDto dto) {

        EmployeeResponseDto updatedResponseDto =
                employeeService.updateEmployee(id, dto);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Employee updated successfully", updatedResponseDto)
        );
    }

    // -------------------- DELETE EMPLOYEE --------------------

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteEmployee(
            @PathVariable Long id) {

        employeeService.deactivateEmployee(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Employee deleted successfully", null)
        );
    }
}

