package com.example.DATN2025.service;

import com.example.DATN2025.dto.AddressShipping.AddressShippingDto;
import com.example.DATN2025.dto.AddressShipping.AddressShippingDtoAdmin;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressShippingService {
    List<AddressShippingDto> getAddressShippingByAccountId();
    AddressShippingDto saveAddressShippingUser(AddressShippingDto addressShippingDto);

    AddressShippingDto saveAddressShippingAdmin(AddressShippingDtoAdmin addressShippingDto);

    void deleteAddressShipping(Long id);
}
