package com.mybank.banking_app.dtos.response;

import com.mybank.banking_app.enums.AddressType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDto {

    private Long addressId;

    private String street;

    private String city;

    private String pincode;

    private String state;

    private AddressType addressType;
}
