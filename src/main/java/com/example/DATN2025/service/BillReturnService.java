package com.example.DATN2025.service;


import com.example.DATN2025.dto.BillReturn.BillReturnCreateDto;
import com.example.DATN2025.dto.BillReturn.BillReturnDetailDto;
import com.example.DATN2025.dto.BillReturn.BillReturnDto;
import com.example.DATN2025.dto.BillReturn.SearchBillReturnDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BillReturnService {
    List<BillReturnDto> getAllBillReturns(SearchBillReturnDto searchBillReturnDto);

    BillReturnDto createBillReturn(BillReturnCreateDto billReturnCreateDto);

    BillReturnDetailDto getBillReturnDetailById(Long id);
    BillReturnDetailDto getBillReturnDetailByCode(String code);

    String generateHtmlContent(Long billReturnId);

    BillReturnDto updateStatus(Long id, int returnStatus);
}
