package com.mybank.banking_app.controllers;

import com.mybank.banking_app.dtos.request.BranchCreateRequestDto;
import com.mybank.banking_app.dtos.request.BranchUpdateRequestDto;
import com.mybank.banking_app.dtos.response.BranchResponseDto;
import com.mybank.banking_app.dtos.response.PageableResponseDto;
import com.mybank.banking_app.utils.ApiResponse;
import com.mybank.banking_app.service.BranchService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    // -------------------- CREATE BRANCH --------------------

    @PostMapping
    public ResponseEntity<ApiResponse<BranchResponseDto>> createBranch(
            @Valid @RequestBody BranchCreateRequestDto dto) {

        BranchResponseDto createdResponseDto = branchService.createBranch(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Branch created successfully", createdResponseDto));
    }

    // -------------------- GET BRANCH BY ID --------------------

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BranchResponseDto>> getBranchById(
            @PathVariable Long id) {

        BranchResponseDto branch = branchService.getBranchById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Branch fetched successfully", branch)
        );
    }

    // -------------------- GET BRANCHES BY BANK --------------------

    @GetMapping("/active/{bankId}")
    public ResponseEntity<ApiResponse<PageableResponseDto<BranchResponseDto>>> getActiveBranches(
            @PathVariable Long bankId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "branchId") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        PageableResponseDto<BranchResponseDto> branches =
                branchService.getActiveBranches(bankId, page, size, sortBy, sortDir);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Branches fetched successfully", branches)
        );
    }

    // -------------------- UPDATE BRANCH --------------------

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BranchResponseDto>> updateBranch(
            @PathVariable Long id,
            @Valid @RequestBody BranchUpdateRequestDto dto) {

        BranchResponseDto updatedResponseDto =
                branchService.updateBranch(id, dto);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Branch updated successfully", updatedResponseDto)
        );
    }

    // -------------------- DELETE (SOFT) BRANCH --------------------

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteBranch(
            @PathVariable Long id) {

        branchService.deactivateBranch(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Branch deactivated successfully", null)
        );
    }
}
