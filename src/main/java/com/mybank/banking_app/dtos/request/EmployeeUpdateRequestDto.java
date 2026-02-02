package com.mybank.banking_app.dtos.request;

import com.mybank.banking_app.enums.EmployeeStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeUpdateRequestDto {

    @NotNull(message = "Employee status is required")
    private EmployeeStatus employeeStatus;

    @NotBlank(message = "Employee title is required")
    private String employeeTitle;

    @Positive(message = "Salary must be positive")
    private BigDecimal employeeSalary;
}
