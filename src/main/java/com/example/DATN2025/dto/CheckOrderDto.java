package com.example.DATN2025.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckOrderDto {
    private Long productDetailId;
    private String quantity;
}
