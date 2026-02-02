package com.mybank.banking_app.controllers;

import com.mybank.banking_app.dtos.request.AddressCreateRequestDto;
import com.mybank.banking_app.dtos.request.AddressUpdateRequestDto;
import com.mybank.banking_app.dtos.response.AddressResponseDto;
import com.mybank.banking_app.utils.ApiResponse;
import com.mybank.banking_app.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    // -------------------- CREATE ADDRESS ---------------------
    @PostMapping
    public ResponseEntity<ApiResponse<AddressResponseDto>> createAddress(
            @Valid @RequestBody AddressCreateRequestDto dto) {
        AddressResponseDto createdResponseDto = addressService.createAddress(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Address created successfully", createdResponseDto));
    }

    // --------------------- GET ADDRESS BY ID -------------------
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AddressResponseDto>> getAddressById(
            @PathVariable Long id) {
        AddressResponseDto responseDto = addressService.getAddressById(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Address fetched successfully", responseDto));
    }

    /*
    // ---------------- GET ALL ADDRESS OF PERSON ------------------
    @GetMapping
    public ResponseEntity<ApiResponse<List<AddressResponseDto>>> getAddressesByPerson(
            @PathVariable Long personId) {
        List<AddressResponseDto> addresses = addressService.getAddressesByPerson(personId);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Address fetched successfully", addresses));
    }
     */

    // ---------------------- UPDATE ADDRESS ------------------------
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AddressResponseDto>> updateAddress(
            @PathVariable Long id,
            @Valid @RequestBody AddressUpdateRequestDto dto) {
        AddressResponseDto updatedResponseDto = addressService.updateAddress(id, dto);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Address updated successfully", updatedResponseDto));
    }

    // ------------------------ DELETE ADDRESS ------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteAddress(@PathVariable Long id) {
        addressService.deactivateAddress(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Address deleted successfully", null));
    }

}

