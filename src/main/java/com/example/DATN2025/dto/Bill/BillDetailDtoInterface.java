package com.example.DATN2025.dto.Bill;

import com.example.DATN2025.entity.enumClass.BillStatus;
import com.example.DATN2025.entity.enumClass.InvoiceType;

import java.time.LocalDateTime;

public interface BillDetailDtoInterface {
    String getMaDonHang();

    String getMaDinhDanh();
    String getDiaChi();

    Double getTongTien();

    Double getTienKhuyenMai();

    String getTenKhachHang();

    String getSoDienThoai();

    String getEmail();

    BillStatus getTrangThaiDonHang();

    String getPhuongThucThanhToan();

    String getMaGiaoDich();

    InvoiceType getLoaiHoaDon();

    String getVoucherName();

    LocalDateTime getCreatedDate();
}
