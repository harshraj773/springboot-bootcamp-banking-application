package com.mybank.banking_app.mappers;

import com.mybank.banking_app.dtos.request.LockerCreateRequestDto;
import com.mybank.banking_app.dtos.response.LockerResponseDto;
import com.mybank.banking_app.entities.Locker;
import com.mybank.banking_app.mappers.config.CentralMapperConfig;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = CentralMapperConfig.class)
public interface LockerMapper {

    Locker toEntity(LockerCreateRequestDto dto);

    LockerResponseDto toResponseDto(Locker locker);

    List<LockerResponseDto> toResponseList(List<Locker> lockers);
}
