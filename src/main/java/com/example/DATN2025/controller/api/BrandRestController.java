package com.example.DATN2025.controller.api;

import com.example.DATN2025.dto.Brand.BrandDto;
import com.example.DATN2025.service.BrandService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class BrandRestController {
    private final BrandService brandService;

    public BrandRestController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping("/api/brand")
    public BrandDto createBrandApi(@RequestBody @Valid BrandDto brandDto) {
        return brandService.createBrandApi(brandDto);
    }
}
