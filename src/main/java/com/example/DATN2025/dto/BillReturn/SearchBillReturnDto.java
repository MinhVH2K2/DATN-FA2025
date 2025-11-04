package com.example.DATN2025.dto.BillReturn;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SearchBillReturnDto {
    private String fromDate;
    private String toDate;
    private String returnStatus;
}
