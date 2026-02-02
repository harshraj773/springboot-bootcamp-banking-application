package com.mybank.banking_app.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankCreateRequestDto {

    @NotBlank(message = "Bank name is required")
    @Size(max = 100, message = "Bank name must not exceed 100 characters")
    private String bankName;

    @NotBlank(message = "Bank code is required")
    @Size(max = 20, message = "Bank code must not exceed 100 characters")
    private String bankCode;

    @NotBlank(message = "Headquater is required")
    private String headquater;
}
