package com.example.DATN2025.entity;

import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"productDetails", "image"}) // tránh vòng lặp
@EqualsAndHashCode(exclude = {"productDetails", "image"})
@Entity
@Table(name = "Product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @Nationalized
    private String name;

    private LocalDateTime createDate;
    private LocalDateTime updatedDate;
    private int status;

    @Column(nullable = false)
    private boolean deleteFlag;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int gender;

    @Nationalized
    private String describe;

    // ====== Quan hệ nhiều-1 (Category, Brand, Material) ======
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brandId")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "materialId")
    private Material material;

    // ====== Quan hệ 1-n với Image ======
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
    private List<Image> image;

    // ====== Quan hệ 1-n với ProductDetail ======
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductDetail> productDetails;
}
