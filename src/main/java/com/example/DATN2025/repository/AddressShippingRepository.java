package com.example.DATN2025.repository;

import com.example.DATN2025.entity.AddressShipping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressShippingRepository extends JpaRepository<AddressShipping, Long> {
    List<AddressShipping> findAllByCustomer_Account_Id(Long accountId);
}
