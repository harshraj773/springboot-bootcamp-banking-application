package com.mybank.banking_app.dtos.request;

import com.mybank.banking_app.enums.CustomerStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUpdateRequestDto {

    @NotNull(message = "Customer status is required")
    private CustomerStatus customerStatus;
}
