package com.example.DATN2025.dto.AddressShipping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressShippingDtoAdmin {
    private Long id;
    private String address;
    private Long customerId;
}
