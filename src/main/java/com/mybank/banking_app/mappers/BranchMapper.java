package com.mybank.banking_app.mappers;

import com.mybank.banking_app.dtos.request.BranchCreateRequestDto;
import com.mybank.banking_app.dtos.request.BranchUpdateRequestDto;
import com.mybank.banking_app.dtos.response.BranchResponseDto;
import com.mybank.banking_app.entities.Branch;
import com.mybank.banking_app.mappers.config.CentralMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(config = CentralMapperConfig.class)
public interface BranchMapper {

    Branch toEntity(BranchCreateRequestDto dto);

    BranchResponseDto toResponseDto(Branch branch);

    List<BranchResponseDto> toResponseList(List<Branch> branches);

    void updateEntityFromDto(BranchUpdateRequestDto dto, @MappingTarget Branch branch);
}
