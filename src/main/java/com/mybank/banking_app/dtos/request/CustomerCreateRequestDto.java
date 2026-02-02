package com.mybank.banking_app.dtos.request;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreateRequestDto {

    @NotNull(message = "Person ID is required")
    private Long personId;
}
