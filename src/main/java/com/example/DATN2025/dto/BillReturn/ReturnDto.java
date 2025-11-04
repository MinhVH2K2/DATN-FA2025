package com.example.DATN2025.dto.BillReturn;

import lombok.Data;

@Data
public class ReturnDto {
    private Long productDetailId;

    //Số lượng khách hàng đổi
    private Integer quantityReturn;
}
