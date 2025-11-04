package com.example.DATN2025.controller.api;


import com.example.DATN2025.dto.Category.CategoryDto;
import com.example.DATN2025.service.CategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryRestController {
    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/api/category")
    public CategoryDto createCategoryApi(@RequestBody CategoryDto categoryDto) {
        return categoryService.createCategoryApi(categoryDto);
    }
}
