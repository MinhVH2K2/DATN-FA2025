package com.example.DATN2025.dto.Bill;

public interface BillDetailProduct {

    Long getId();
    Long getProductId();

    String getBillDetailId();
    String getProductCode();
    String getImageUrl();
    String getTenSanPham();

    String getTenMau();

    String getKichCo();

    Double getGiaTien();

    int getSoLuong();

    Double getTongTien();
}
