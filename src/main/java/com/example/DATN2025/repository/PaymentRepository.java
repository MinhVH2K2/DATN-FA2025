package com.example.DATN2025.repository;


import com.example.DATN2025.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    boolean existsByOrderId(String orderId);
    Payment findByOrderId(String orderId);

}
