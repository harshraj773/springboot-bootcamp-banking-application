package com.mybank.banking_app.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankUpdateRequestDto {

    @NotBlank(message = "Bank name is required")
    private String bankName;

    @NotBlank(message = "Headquater is required")
    private String headquater;
}
