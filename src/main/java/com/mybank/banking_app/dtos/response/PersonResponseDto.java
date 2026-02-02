package com.mybank.banking_app.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponseDto {

    private Long personId;

    private String fullName;

    private String email;

    private String panNumber;

    private List<AddressResponseDto> addresses;
}
