package com.mybank.banking_app.entities;

import com.mybank.banking_app.enums.EmployeeStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(name = "employee_title", nullable = false)
    private String employeeTitle;

    @Column(name = "employee_salary", nullable = false)
    private BigDecimal employeeSalary;

    @Enumerated(EnumType.STRING)
    @Column(name = "employee_status", nullable = false)
    private EmployeeStatus employeeStatus;

    @Column(name = "employee_joining_date", nullable = false)
    private LocalDate joiningDate;
}
