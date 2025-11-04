package com.example.DATN2025.dto.Statistic;


public interface ProductStatistic {
    String getCode();
    String getName();
    String getBrand();
    String getCategory();
//    String getImageUrl();
    int getTotalQuantity();
    int getTotalQuantityReturn();
    Double getRevenue();
}
