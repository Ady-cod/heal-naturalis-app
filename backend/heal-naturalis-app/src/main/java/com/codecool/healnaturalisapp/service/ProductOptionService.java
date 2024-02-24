package com.codecool.healnaturalisapp.service;

import com.codecool.healnaturalisapp.dto.ProductOptionDTO;
import com.codecool.healnaturalisapp.model.ProductOption;
import com.codecool.healnaturalisapp.repository.ProductOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductOptionService {

    private final ProductOptionRepository productOptionRepository;

    public ProductOption getProductOptionById(long productOptionId) {
        return productOptionRepository.findById(productOptionId).orElse(null);
    }

    public List<ProductOptionDTO> convertToDTO(List<ProductOption> productOptions) {
        if (productOptions == null || productOptions.isEmpty()) {
            return Collections.emptyList();
        }
        return productOptions.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public ProductOptionDTO convertToDTO(ProductOption productOption) {
        if (productOption == null) {
            return null;
        }
        return ProductOptionDTO.builder()
                .id(productOption.getId())
                .name(productOption.getName())
                .build();
    }

    public ProductOption convertFromDTO(ProductOptionDTO productOptionDTO) {
        if (productOptionDTO == null) {
            return null;
        }
        return ProductOption.builder()
                .id(productOptionDTO.getId())
                .name(productOptionDTO.getName())
                .build();
    }

    public List<ProductOption> convertFromDTO(List<ProductOptionDTO> productOptionDTOs) {
        if (productOptionDTOs == null || productOptionDTOs.isEmpty()) {
            return new ArrayList<>();
        }
        return productOptionDTOs.stream()
                .map(this::convertFromDTO)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
