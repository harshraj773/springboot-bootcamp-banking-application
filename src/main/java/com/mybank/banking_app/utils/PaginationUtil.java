package com.mybank.banking_app.utils;

import com.mybank.banking_app.dtos.response.PageableResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaginationUtil {
    public static <E, D>PageableResponseDto<D> getPageableResponse(Page<E> page, List<D> dtoList) {
        PageableResponseDto<D> response = new PageableResponseDto<>();
        response.setContent(dtoList);
        response.setPage(page.getNumber());
        response.setSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setTotalElements(page.getTotalElements());
        response.setLast(page.isLast());
        return response;
    }
}
