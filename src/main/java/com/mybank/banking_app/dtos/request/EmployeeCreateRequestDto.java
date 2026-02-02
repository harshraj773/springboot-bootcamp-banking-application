package com.mybank.banking_app.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCreateRequestDto {

    @NotNull(message = "Person ID is required")
    private Long personId;

    @NotBlank(message = "Title is required")
    private String employeeTitle;

    @NotNull(message = "Salary is required")
    @Positive(message = "Salary must be positive")
    private BigDecimal employeeSalary;

    @NotNull(message = "Joining date cannot be null")
    @PastOrPresent(message = "Joining date cannot be in future")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate joiningDate;
}
