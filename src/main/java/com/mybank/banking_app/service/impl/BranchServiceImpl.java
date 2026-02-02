package com.mybank.banking_app.service.impl;

import com.mybank.banking_app.dtos.request.BranchCreateRequestDto;
import com.mybank.banking_app.dtos.request.BranchUpdateRequestDto;
import com.mybank.banking_app.dtos.response.BranchResponseDto;
import com.mybank.banking_app.dtos.response.PageableResponseDto;
import com.mybank.banking_app.entities.Bank;
import com.mybank.banking_app.entities.Branch;
import com.mybank.banking_app.enums.BranchStatus;
import com.mybank.banking_app.exceptions.ResourceNotFoundException;
import com.mybank.banking_app.mappers.BranchMapper;
import com.mybank.banking_app.repositories.BankRepository;
import com.mybank.banking_app.repositories.BranchRepository;
import com.mybank.banking_app.service.BranchService;
import com.mybank.banking_app.utils.PaginationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final BankRepository bankRepository;
    private final BranchMapper branchMapper;

    public BranchServiceImpl(BankRepository bankRepository,
                             BranchRepository branchRepository,
                             BranchMapper branchMapper){
        this.bankRepository = bankRepository;
        this.branchMapper = branchMapper;
        this.branchRepository = branchRepository;
    }

    @Override
    public BranchResponseDto createBranch(BranchCreateRequestDto dto) {
        Bank bank = bankRepository.findById(dto.getBankId())
                .orElseThrow(() -> new ResourceNotFoundException("Bank not found with id: "+ dto.getBankId()));
        Branch branch = branchMapper.toEntity(dto);
        branch.setBranchStatus(BranchStatus.ACTIVE);
        branch.setBank(bank);
        Branch savedBranch = branchRepository.save(branch);

        return branchMapper.toResponseDto(savedBranch);
    }

    @Override
    public BranchResponseDto updateBranch(Long branchId, BranchUpdateRequestDto dto) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: "+branchId));

        branchMapper.updateEntityFromDto(dto, branch);
        Branch updatedBranch = branchRepository.save(branch);
        return branchMapper.toResponseDto(updatedBranch);
    }

    @Override
    public BranchResponseDto getBranchById(Long branchId) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + branchId));

        if(branch.getBranchStatus() == BranchStatus.INACTIVE)
            throw new ResourceNotFoundException("Branch Inactive");

        return branchMapper.toResponseDto(branch);
    }

    @Override
    public PageableResponseDto<BranchResponseDto> getActiveBranches(Long bankId, int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Branch> branchPage = branchRepository.findByBankBankIdAndBranchStatus(bankId, BranchStatus.ACTIVE, pageable);
        List<BranchResponseDto> branchResponseDtoList = branchMapper.toResponseList(branchPage.getContent());

        return PaginationUtil.getPageableResponse(branchPage, branchResponseDtoList);
    }

    @Override
    public void deactivateBranch(Long branchId) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: "+ branchId));
        branch.setBranchStatus(BranchStatus.INACTIVE);
        branchRepository.save(branch);
    }
}
