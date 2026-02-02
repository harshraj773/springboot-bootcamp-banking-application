package com.mybank.banking_app.dtos.response;

import com.mybank.banking_app.enums.EmployeeStatus;
import jakarta.persistence.*;
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
public class EmployeeResponseDto {

    private Long employeeId;

    private PersonResponseDto person;

    private String employeeTitle;

    private BigDecimal employeeSalary;

    private EmployeeStatus employeeStatus;

    private LocalDate joiningDate;
}
