package com.mybank.banking_app.controllers;

import com.mybank.banking_app.dtos.request.PersonCreateRequestDto;
import com.mybank.banking_app.dtos.request.PersonUpdateRequestDto;
import com.mybank.banking_app.dtos.response.CustomerResponseDto;
import com.mybank.banking_app.dtos.response.PageableResponseDto;
import com.mybank.banking_app.dtos.response.PersonResponseDto;
import com.mybank.banking_app.utils.ApiResponse;
import com.mybank.banking_app.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonService personService;
    public PersonController(PersonService personService) {

        this.personService = personService;
    }

    // ---------------- CREATE----------------
    @PostMapping
    public ResponseEntity<ApiResponse<PersonResponseDto>> createPerson(
            @Valid @RequestBody PersonCreateRequestDto dto
            ) {
        PersonResponseDto createdResponseDto = personService.createPerson(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Person created successfully", createdResponseDto));
    }

    // ------------- GET PERSON BY ID ----------
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PersonResponseDto>> getPersonById(@PathVariable Long id){
        PersonResponseDto responseDto = personService.getPersonById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Person fetched successfully", responseDto)
        );
    }

    // ---------------- GET ALL PERSONS ----------------------
    @GetMapping
    public ResponseEntity<ApiResponse<PageableResponseDto<PersonResponseDto>>> getAllPersons(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "personId") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        PageableResponseDto<PersonResponseDto> persons = personService.getAllPersons(page, size, sortBy, sortDir);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Customers fetched successfully", persons));
    }

    /*
    I don't think this required, as after creating address of a person, by get : person by id will also return the address
    associated with that person.

    // --------------- GET ADDRESS OF PERSON BY ID --------
    @GetMapping("/person/{personId}/addresses")
    public ResponseEntity<ApiResponse<List<AddressResponseDto>>> getAddressesOfPerson(@PathVariable Long personId) {
        List<AddressResponseDto> addresses = addressService.getAddressesByPerson(personId);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Addresses of person fetched successfully",addresses));
    }

     */

    // --------------- UPDATE PERSON -------------
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PersonResponseDto>> updatePerson(
            @PathVariable Long id,
            @Valid @RequestBody PersonUpdateRequestDto dto) {

        PersonResponseDto updatedResponseDto = personService.updatePerson(id, dto);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Person updated successfully", updatedResponseDto));
    }

    // --------------- DELETE PERSON ---------------
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deletePerson(@PathVariable Long id) {
        personService.deactivatePerson(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Person successfully deleted", null));
    }
}
