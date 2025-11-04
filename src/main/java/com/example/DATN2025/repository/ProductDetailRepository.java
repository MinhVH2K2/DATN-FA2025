package com.example.DATN2025.repository;

import com.example.DATN2025.entity.Product;
import com.example.DATN2025.entity.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {

    ProductDetail findByBarcode(String barcode);
    Page<ProductDetail> getProductDetailsByProductId(Long id, Pageable pageable);

    ProductDetail getProductDetailByProduct(Product product);
    List<ProductDetail> getProductDetailByProductId(Long productId);

    ProductDetail findByBarcodeContainingIgnoreCase(String barcode);

    boolean existsByBarcode(String barcode);

}