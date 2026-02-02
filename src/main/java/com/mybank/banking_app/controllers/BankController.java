package com.mybank.banking_app.controllers;

import com.mybank.banking_app.dtos.request.BankCreateRequestDto;
import com.mybank.banking_app.dtos.request.BankUpdateRequestDto;
import com.mybank.banking_app.dtos.response.BankResponseDto;
import com.mybank.banking_app.utils.ApiResponse;
import com.mybank.banking_app.service.BankService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/banks")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    // -------------------- CREATE BANK --------------------

    @PostMapping
    public ResponseEntity<ApiResponse<BankResponseDto>> createBank(
            @Valid @RequestBody BankCreateRequestDto dto) {

        BankResponseDto created = bankService.createBank(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Bank created successfully", created));
    }

    /* -------------------- GET BANK BY ID --------------------

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BankResponseDto>> getBankById(
            @PathVariable Long id) {

        BankResponseDto bank = bankService.getBankById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Bank fetched successfully", bank)
        );
    }

     */

    /* -------------------- GET ALL BANKS --------------------

    @GetMapping
    public ResponseEntity<ApiResponse<List<BankResponseDto>>> getAllBanks() {

        List<BankResponseDto> banks = bankService.getAllBanks();

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Banks fetched successfully", banks)
        );
    }

     */

    // -------------------- UPDATE BANK --------------------

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BankResponseDto>> updateBank(
            @PathVariable Long id,
            @Valid @RequestBody BankUpdateRequestDto dto) {

        BankResponseDto updated = bankService.updateBank(id, dto);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Bank updated successfully", updated)
        );
    }

    /* -------------------- DELETE BANK --------------------

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteBank(
            @PathVariable Long id) {

        bankService.deleteBank(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Bank deleted successfully", null)
        );
    }

     */
}