package com.example.DATN2025.controller.admin;

import com.example.DATN2025.dto.ProductDiscount.ProductDiscountCreateDto;
import com.example.DATN2025.dto.ProductDiscount.ProductDiscountDto;
import com.example.DATN2025.entity.Category;
import com.example.DATN2025.entity.ProductDiscount;
import com.example.DATN2025.repository.ProductDetailRepository;
import com.example.DATN2025.repository.ProductDiscountRepository;
import com.example.DATN2025.repository.ProductRepository;
import com.example.DATN2025.service.CategoryService;
import com.example.DATN2025.service.ProductDiscountService;
import com.example.DATN2025.service.ProductService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductDiscountController {
    private final ProductService productService;

    private final ProductDiscountService productDiscountService;
    private final CategoryService categoryService;
    private final ProductDetailRepository productDetailRepository;
    private final ProductDiscountRepository productDiscountRepository;

    public ProductDiscountController(ProductService productService, ProductRepository productRepository, ProductDiscountService productDiscountService, CategoryService categoryService, ProductDetailRepository productDetailRepository, ProductDiscountRepository productDiscountRepository) {
        this.productService = productService;
        this.productDiscountService = productDiscountService;
        this.categoryService = categoryService;
        this.productDetailRepository = productDetailRepository;
        this.productDiscountRepository = productDiscountRepository;
    }

    @GetMapping("/admin-only/product-discount")
    public String viewProductDiscountPage(Model model, Pageable pageable) {
        List<ProductDiscount> productDiscountList = productDiscountRepository.findAll();
        model.addAttribute("productDiscounts", productDiscountList);
        return "/admin/product-discount";
    }

    @GetMapping("/admin-only/product-discount-create")
    public String viewProductDiscountCreatePage(Model model) {
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        return "/admin/product-discount-create";
    }

    @ResponseBody
    @PostMapping("/api/private/product-discount/multiple")
    public List<ProductDiscountDto> createProductDiscountMultiple(@Valid @RequestBody ProductDiscountCreateDto productDiscountCreateDto) {
        return productDiscountService.createProductDiscountMultiple(productDiscountCreateDto);
    }

    @ResponseBody
    @PostMapping("/api/private/product-discount/{id}/status/{status}")
    public ProductDiscountDto updateProductDiscount(@Valid @PathVariable Long id, @PathVariable boolean status) {
        return productDiscountService.updateCloseProductDiscount(id, status);
    }
}
