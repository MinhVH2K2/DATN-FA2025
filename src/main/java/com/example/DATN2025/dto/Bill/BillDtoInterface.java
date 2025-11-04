package com.example.DATN2025.dto.Bill;

import com.example.DATN2025.entity.enumClass.BillStatus;
import com.example.DATN2025.entity.enumClass.InvoiceType;

import java.time.LocalDateTime;
import java.util.Date;

public interface BillDtoInterface {
    Long getMaHoaDon();
    String getMaDinhDanh();
    String getHoVaTen();
    String getSoDienThoai();
    LocalDateTime getNgayTao();
    Double getTongTien();
    BillStatus getTrangThai();
    InvoiceType getLoaiDon();
    String getHinhThucThanhToan();

    String getMaGiaoDich();
    String getMaDoiTra();
}
