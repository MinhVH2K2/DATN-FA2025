package com.example.DATN2025.service;


import com.example.DATN2025.dto.Product.ProductDetailDto;
import com.example.DATN2025.entity.ProductDetail;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductDetailService {
    ProductDetail save(ProductDetail productDetail);

    ProductDetail getProductDetailByProductCode(String code) throws NotFoundException;

    List<ProductDetailDto> getByProductId(Long id) throws com.example.DATN2025.exception.NotFoundException;
}
