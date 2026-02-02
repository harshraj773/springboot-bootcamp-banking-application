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
public class LockerUpdateRequestDto {

    @NotBlank(message = "Locker number is required")
    private String lockerNumber;
}
