package com.mybank.banking_app.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LockerCreateRequestDto {

    @NotBlank(message = "Locker number is required")
    private String lockerNumber;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Branch ID is required")
    private Long branchId;
}
