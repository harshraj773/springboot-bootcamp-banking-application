package com.mybank.banking_app.dtos.request;

import com.mybank.banking_app.enums.BranchStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchUpdateRequestDto {

    @NotBlank(message = "Branch name is required")
    private String branchName;

    @NotNull(message = "Branch status is required")
    private BranchStatus branchStatus;

    @NotBlank(message = "Branch code is required")
    private String branchCode;
}
