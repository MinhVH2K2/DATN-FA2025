package com.example.DATN2025.dto.Order;


import lombok.Data;

@Data
public class OrderDetailDto {
    private Integer quantity;
    private Long productDetailId;
}
