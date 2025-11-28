package com.example.DATN2025.repository;

import com.example.DATN2025.entity.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;



public interface VerificationRepository extends JpaRepository<VerificationCode, Long> {
    VerificationCode findByCode(String code);
}
