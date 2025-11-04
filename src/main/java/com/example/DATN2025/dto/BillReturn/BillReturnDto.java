package com.example.DATN2025.dto.BillReturn;


import com.example.DATN2025.dto.CustomerDto.CustomerDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BillReturnDto {

    private Long id;

    private String code;

    private String returnReason;

    private CustomerDto customer;

    private LocalDateTime returnDate;

    private Double returnMoney;

    private boolean isCancel;

    private int returnStatus;

}
