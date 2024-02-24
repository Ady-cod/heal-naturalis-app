package com.codecool.healnaturalisapp.dto;

import com.codecool.healnaturalisapp.model.ProductOptionValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductOptionValueDTO {

    private long id;

    private String value;

    private ProductOptionDTO productOption;

//    public static List<ProductOptionValueDTO> convertToDTO(List<ProductOptionValue> productOptionValues) {
//        if (productOptionValues == null || productOptionValues.isEmpty()) {
//            return Collections.emptyList();
//        }
//        return productOptionValues.stream()
//                .map(ProductOptionValueDTO::convertToDTO)
//                .toList();
//    }
//
//    public static ProductOptionValueDTO convertToDTO(ProductOptionValue productOptionValue) {
//        if (productOptionValue == null) {
//            return null;
//        }
//        return ProductOptionValueDTO.builder()
//                .id(productOptionValue.getId())
//                .value(productOptionValue.getValue())
//                .productOption(.convertToDTO()productOptionValue.getProductOption().getId())
//                .build();
//    }
//
//    public static ProductOptionValue convertFromDTO(ProductOptionValueDTO productOptionValueDTO) {
//        if (productOptionValueDTO == null) {
//            return null;
//        }
//        return ProductOptionValue.builder()
//                .id(productOptionValueDTO.getId())
//                .value(productOptionValueDTO.getValue())
//                .productOption(productOptionRepository.getProductOptionById(productOptionValueDTO
//                        .getProductOptionId()))
//                .build();
//    }
//
//    public static List<ProductOptionValue> convertFromDTO(List<ProductOptionValueDTO> productOptionValueDTOs) {
//        if (productOptionValueDTOs == null || productOptionValueDTOs.isEmpty()) {
//            return Collections.emptyList();
//        }
//        return productOptionValueDTOs.stream()
//                .map(this::convertFromDTO)
//                .toList();
//    }

}
