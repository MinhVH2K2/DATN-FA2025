package com.example.DATN2025.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"product"}) //
@EqualsAndHashCode(exclude = {"product"})
@Entity
@Table(name = "ProductDetail")
public class ProductDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;
    private double price;

    @Column(name = "barcode")
    private String barcode;

    @Column(nullable = false)
    private Boolean deleteFlag;
    // ====== Quan hệ nhiều-1 với Product ======
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    @JsonIgnore
    private Product product;


    @ManyToOne
    @JoinColumn(name = "sizeId")
    private Size size;


    @ManyToOne
    @JoinColumn(name = "colorId")
    private Color color;


    @OneToOne(mappedBy = "productDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProductDiscount productDiscount;
}
