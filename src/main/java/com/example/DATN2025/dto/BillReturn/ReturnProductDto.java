package com.example.DATN2025.dto.BillReturn;

import lombok.Data;

@Data
public class ReturnProductDto {
    private String productName;
    private Long productDetailId;
    private String size;
    private String color;

    //Giá đổi
    private Double momentPriceExchange;

    //Số lượng đổi
    private Integer quantityReturn;
}
