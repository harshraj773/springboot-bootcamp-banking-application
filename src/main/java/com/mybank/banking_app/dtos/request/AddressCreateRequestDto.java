package com.mybank.banking_app.dtos.request;

import com.mybank.banking_app.enums.AddressType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressCreateRequestDto {

        @NotBlank(message = "Street address is required")
        private String street;

        @NotBlank(message = "City is required")
        private String city;

        @NotBlank(message = "Pincode is required")
        @Pattern(regexp = "^[1-9][0-9]{5}$", message = "Pincode must contain only digits")
        private String pincode;

        @NotBlank(message = "State is required")
        private String state;

        @NotNull(message = "Address type is required")
        private AddressType addressType;

        // either branch or person id
        private Long branchId;

        private Long personId;
}
