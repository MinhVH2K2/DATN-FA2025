package com.example.DATN2025.dto.Bill;

import com.example.DATN2025.dto.CustomerDto.CustomerDto;
import com.example.DATN2025.entity.enumClass.BillStatus;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
public class BillDto {
    private Long id;
    private String code;
    private double promotionPrice;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private BillStatus status;
    private Boolean returnStatus;
    private CustomerDto customer;
    private Double totalAmount;
}
