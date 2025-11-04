package com.example.DATN2025.dto.Product;


import com.example.DATN2025.entity.ProductDetail;
import lombok.Data;

import java.util.List;

@Data
public class CreateProductDetailsForm {
    private List<ProductDetail> productDetailList;
}
