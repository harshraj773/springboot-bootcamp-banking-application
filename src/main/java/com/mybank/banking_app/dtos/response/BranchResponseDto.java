package com.mybank.banking_app.dtos.response;
import com.mybank.banking_app.enums.BranchStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class BranchResponseDto {

    private Long branchId;

    private String branchName;

    private String branchCode;

    private String branchIfscCode;

    private BranchStatus branchStatus;

    private BankResponseDto bank;

    private List<AccountResponseDto> accounts;

    private AddressResponseDto address;

    private List<LockerResponseDto> lockers;
}
