package com.example.DATN2025.service;

import com.example.DATN2025.dto.ProductDiscount.ProductDiscountCreateDto;
import com.example.DATN2025.dto.ProductDiscount.ProductDiscountDto;
import com.example.DATN2025.entity.ProductDiscount;
import java.util.List;

public interface ProductDiscountService {
    List<ProductDiscount> getAllProductDiscount();

    ProductDiscountDto updateCloseProductDiscount(Long discountId, boolean closed);

    List<ProductDiscountDto> createProductDiscountMultiple(ProductDiscountCreateDto productDiscountCreateDto);
}
