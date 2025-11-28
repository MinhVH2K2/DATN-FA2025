package com.example.DATN2025.controller.user;

import com.example.DATN2025.dto.Product.ProductDto;
import com.example.DATN2025.dto.Product.SearchProductDto;
import com.example.DATN2025.entity.Category;
import com.example.DATN2025.service.CategoryService;
import com.example.DATN2025.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String gethome(Model model, SearchProductDto searchProductDto,
                          @PageableDefault(size = 20, sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable) {

        if (searchProductDto == null) {
            searchProductDto = new SearchProductDto(); // Khởi tạo mặc định
        }

        List<Category> categories = categoryService.getAll();
        Page<ProductDto> products = productService.searchProduct(searchProductDto, pageable);

        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        Sort sort = pageable.getSort();
        String url = "";

        if(searchProductDto.getKeyword() != null) {
            url += "&keyword=" + searchProductDto.getKeyword();
        }

        if(sort.isSorted()) {
            List<String> sortStrings = sort.toList().stream()
                    .map(order -> order.getProperty() + "," + (order.isDescending() ? "desc" : "asc"))
                    .collect(Collectors.toList());

            url += "&sort=" + String.join(",", sortStrings);
            searchProductDto.setSort(String.join(",", sortStrings));
        }

        if(searchProductDto.getMinPrice() != null) {
            url += "&minPrice=" + searchProductDto.getMinPrice();
        }
        if(searchProductDto.getMaxPrice() != null) {
            url += "&maxPrice=" + searchProductDto.getMaxPrice();
        }
        if(searchProductDto.getCategoryId() != null) {
            url += "&category=" + searchProductDto.getCategoryId().stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(","));
        }
        if(searchProductDto.getGender() != null) {
            url += "&gender=" + searchProductDto.getGender();
        }

        model.addAttribute("url", url);
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("dataFilter", searchProductDto);

        return "user/home-03";
    }





}
