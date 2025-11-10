package com.example.DATN2025.service;

import com.example.DATN2025.dto.DiscountCode.DiscountCodeDto;
import com.example.DATN2025.dto.DiscountCode.SearchDiscountCodeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface DiscountCodeService {
    Page<DiscountCodeDto> getAllDiscountCode(SearchDiscountCodeDto searchDiscountCodeDto, Pageable pageable);
    DiscountCodeDto saveDiscountCode(DiscountCodeDto discountCodeDto);
    DiscountCodeDto updateDiscountCode(DiscountCodeDto discountCodeDto);

    DiscountCodeDto getDiscountCodeById(Long id);
    DiscountCodeDto getDiscountCodeByCode(Long code);
    DiscountCodeDto updateStatus(Long discountCodeId, int status);
    Page<DiscountCodeDto> getAllAvailableDiscountCode(Pageable pageable);
}
