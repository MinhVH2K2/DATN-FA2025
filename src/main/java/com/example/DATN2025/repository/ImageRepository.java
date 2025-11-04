package com.example.DATN2025.repository;

import com.example.DATN2025.entity.Image;

import com.example.DATN2025.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findAllByProduct(Product product);
    Image findImageById(Long id);
}