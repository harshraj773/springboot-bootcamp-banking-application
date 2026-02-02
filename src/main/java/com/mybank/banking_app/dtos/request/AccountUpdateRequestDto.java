package com.mybank.banking_app.dtos.request;

import com.mybank.banking_app.enums.AccountStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountUpdateRequestDto {
    @NotNull(message = "Account status is required")
    private AccountStatus status;
}
