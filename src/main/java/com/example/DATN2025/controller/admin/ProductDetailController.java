package com.example.DATN2025.controller.admin;


import com.example.DATN2025.entity.*;
import com.example.DATN2025.repository.ProductDetailRepository;
import com.example.DATN2025.service.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductDetailController {

    private Product productInLine;
    private final List<Image> imageList = new ArrayList<>();
    private long idImage;

    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Autowired
    private ProductService productService;

//    @Autowired
//    private SizeService sizeService;
    @Autowired
    private ColorService colorService;

    @Autowired
    private ImageService imageService;

    @GetMapping("/admin/chi-tiet-san-pham/{code}")
    public String getProductDetailPage(@PathVariable String code, Model model) {
        Product product = productService.getProductByCode(code);
        if(product != null) {
            model.addAttribute("product", product);
            model.addAttribute("productDetails", product.getProductDetails());
            return "admin/product-detail";
        }

        return "error/404";
    }

//    @ModelAttribute("listSize")
//    public List<Size> getSize() {
//        return sizeService.getAll();
//    }

    @ModelAttribute("listColor")
    public List<Color> getColor() {
        return colorService.findAll();
    }


    @PostMapping("admin/version/update-status/{id}")
    public String setDeleteFlag(@PathVariable String id , RedirectAttributes redirectAttributes) throws NotFoundException {
    ProductDetail productDetail =  productDetailRepository.findByBarcode(id);
      String codeProduct= productDetail.getProduct().getCode();
        productDetail.setDeleteFlag(!productDetail.getDeleteFlag());
        System.out.print(productDetail.getQuantity());
       productDetailRepository.save(productDetail);
        return "redirect:/admin/chi-tiet-san-pham/" + codeProduct;

    }
}
