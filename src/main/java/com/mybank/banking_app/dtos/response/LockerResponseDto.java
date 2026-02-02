package com.mybank.banking_app.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LockerResponseDto {

    private Long lockerId;

    private String lockerNumber;

    private CustomerResponseDto customer;

    private BranchResponseDto branch;
}
