package com.mybank.banking_app.service;

import com.mybank.banking_app.dtos.request.BranchCreateRequestDto;
import com.mybank.banking_app.dtos.request.BranchUpdateRequestDto;
import com.mybank.banking_app.dtos.response.BranchResponseDto;
import com.mybank.banking_app.dtos.response.PageableResponseDto;

import java.util.List;

public interface BranchService {

    BranchResponseDto createBranch(BranchCreateRequestDto dto);

    BranchResponseDto updateBranch(Long branchId, BranchUpdateRequestDto dto);

    BranchResponseDto getBranchById(Long branchId);

    PageableResponseDto<BranchResponseDto> getActiveBranches(Long bankId, int page, int size, String sortBy, String sortDir);

    void deactivateBranch(Long branchId);
}
