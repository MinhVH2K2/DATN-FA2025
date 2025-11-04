package com.example.DATN2025.repository;

import com.example.DATN2025.entity.Brand;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    boolean existsByCode(String code);
    List<Brand> findAllByDeleteFlagFalse();
}