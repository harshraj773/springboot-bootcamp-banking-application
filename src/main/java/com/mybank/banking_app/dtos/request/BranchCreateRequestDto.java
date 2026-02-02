package com.mybank.banking_app.dtos.request;
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
public class BranchCreateRequestDto {

    @NotBlank(message = "Branch name is required")
    private String branchName;

    @NotBlank(message = "Branch code is required")
    private String branchCode;

    @NotBlank(message = "IFSC code is required")
    @Pattern(regexp = "^[A-Z]{4}0[A-Z0-9]{6}$", message = "Invalid IFSC code")
    private String branchIfscCode;

    @NotNull(message = "Bank ID is required")
    private Long bankId;
}
