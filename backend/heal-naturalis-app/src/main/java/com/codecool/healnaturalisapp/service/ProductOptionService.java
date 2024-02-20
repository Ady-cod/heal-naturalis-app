package com.codecool.healnaturalisapp.service;

import com.codecool.healnaturalisapp.dto.ProductOptionDTO;
import com.codecool.healnaturalisapp.model.Product;
import com.codecool.healnaturalisapp.model.ProductOption;
import com.codecool.healnaturalisapp.repository.ProductOptionRepository;
import com.codecool.healnaturalisapp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductOptionService {
    private final ProductOptionRepository productOptionRepository;

    private final ProductOptionValueService productOptionValueService;

    private final ProductRepository productRepository;

    public List<ProductOption> getAllProductOptionsByProductId(long productId) {
        return productOptionRepository.findAllByProducts_Id(productId);
    }

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
                .productIds(productOption.getProducts().stream()
                        .map(Product::getId)
                        .toList())
                .productOptionValues(productOptionValueService.convertToDTO(productOption.getProductOptionValues()))
                .build();
    }

    public ProductOption convertFromDTO(ProductOptionDTO productOptionDTO) {
        if (productOptionDTO == null) {
            return null;
        }
        ProductOption productOptionConverted = ProductOption.builder()
                .id(productOptionDTO.getId())
                .name(productOptionDTO.getName())
                .products(productOptionDTO.getProductIds() == null ? null : productOptionDTO.getProductIds().stream()
                        .map(productRepository::getProductById)
                        .toList())
                .productOptionValues(productOptionValueService.convertFromDTO(productOptionDTO.getProductOptionValues()))
                .build();
        if (productOptionConverted.getProductOptionValues()!=null) {
            productOptionConverted.getProductOptionValues()
                    .forEach(productOptionValue -> productOptionValue.setProductOption(productOptionConverted));
        }
        return productOptionConverted;
    }

    public List<ProductOption> convertFromDTO(List<ProductOptionDTO> productOptionDTOs) {
        if (productOptionDTOs == null || productOptionDTOs.isEmpty()) {
            return Collections.emptyList();
        }
        return productOptionDTOs.stream()
                .map(this::convertFromDTO)
                .toList();
    }
}
