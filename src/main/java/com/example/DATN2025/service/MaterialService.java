package com.example.DATN2025.service;

import com.example.DATN2025.dto.Material.MaterialDto;
import com.example.DATN2025.entity.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface MaterialService {

    Page<Material> getAllMaterial(Pageable pageable);

    Material save(Material material);

    Material createMaterial(Material material);
    Material updateMaterial(Material material);

    void delete(Long id);

    Optional<Material> findById(Long id);
    List<Material> getAll();

    MaterialDto createMaterialApi(MaterialDto materialDto);
}
