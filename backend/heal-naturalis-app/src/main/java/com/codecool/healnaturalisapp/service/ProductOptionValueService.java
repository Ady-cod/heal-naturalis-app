package com.codecool.healnaturalisapp.service;

import com.codecool.healnaturalisapp.dto.ProductOptionValueDTO;
import com.codecool.healnaturalisapp.model.ProductOptionValue;
import com.codecool.healnaturalisapp.repository.ProductOptionRepository;
import com.codecool.healnaturalisapp.repository.ProductOptionValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductOptionValueService {
    private final ProductOptionValueRepository productOptionValueRepository;
    private final ProductOptionRepository productOptionRepository;
    private final ProductOptionService productOptionService;

    public List<ProductOptionValueDTO> getAllProductOptionValuesByProductOptionId(long productOptionId) {
        List<ProductOptionValue> productOptionValues = productOptionValueRepository.findAllByProductOptionId(productOptionId);
        return convertToDTO(productOptionValues);
    }

    public List<ProductOptionValueDTO> convertToDTO(List<ProductOptionValue> productOptionValues) {
        if (productOptionValues == null || productOptionValues.isEmpty()) {
            return Collections.emptyList();
        }
        return productOptionValues.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public ProductOptionValueDTO convertToDTO(ProductOptionValue productOptionValue) {
        if (productOptionValue == null) {
            return null;
        }
        return ProductOptionValueDTO.builder()
                .id(productOptionValue.getId())
                .value(productOptionValue.getValue())
                .productOption(productOptionService.convertToDTO(productOptionValue.getProductOption()))
                .build();
    }

    public ProductOptionValue convertFromDTO(ProductOptionValueDTO productOptionValueDTO) {
        if (productOptionValueDTO == null) {
            return null;
        }
        return ProductOptionValue.builder()
                .id(productOptionValueDTO.getId())
                .value(productOptionValueDTO.getValue())
                .productOption(productOptionRepository.getProductOptionById(productOptionValueDTO
                                .getProductOption().getId()))
                .build();
    }

    public List<ProductOptionValue> convertFromDTO(List<ProductOptionValueDTO> productOptionValueDTOs) {
        if (productOptionValueDTOs == null || productOptionValueDTOs.isEmpty()) {
            return Collections.emptyList();
        }
        return productOptionValueDTOs.stream()
                .map(this::convertFromDTO)
                .toList();
    }

}
