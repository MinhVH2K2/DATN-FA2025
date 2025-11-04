package com.example.DATN2025.service;


import com.example.DATN2025.entity.Image;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ImageService {
    List<Image> getAllImagesByProductId(Long productId);
    void removeImageByIds(List<Long> ids);
}
