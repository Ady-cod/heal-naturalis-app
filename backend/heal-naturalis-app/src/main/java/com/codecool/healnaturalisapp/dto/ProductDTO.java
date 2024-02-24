package com.codecool.healnaturalisapp.dto;

import com.codecool.healnaturalisapp.model.CartItem;
import com.codecool.healnaturalisapp.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private long id;

    private int stock;

    private double price;

    private String imageUrl;

    private CategoryDTO category;

    private List<ProductOptionValueDTO> productOptionValues = new ArrayList<>();

    private List<Long> cartItemsIds;

//    public static List<ProductDTO> convertToDTO(List<Product> products) {
//        if (products == null || products.isEmpty()) {
//            return Collections.emptyList();
//        }
//        return products.stream()
//                .map(this::convertToDTO)
//                .toList();
//    }
//
//    public static ProductDTO convertToDTO(Product product) {
//        if (product == null) {
//            return null;
//        }
//        return ProductDTO.builder()
//                .id(product.getId())
//                .stock(product.getStock())
//                .price(product.getPrice())
//                .imageUrl(product.getImageUrl())
//                .category(categoryService.convertToDTO(product.getCategory()))
//                .productOptionValues(productOptionValueService.convertToDTO(product.getProductOptionValues()))
//                .cartItemsIds(product.getCartItems().stream().map(CartItem::getId).toList())
//                .build();
//    }
//
//    public static Product convertFromDTO(ProductDTO productDTO) {
//        if (productDTO == null) {
//            return null;
//        }
//        return Product.builder()
//                .id(productDTO.getId())
//                .stock(productDTO.getStock())
//                .price(productDTO.getPrice())
//                .imageUrl(productDTO.getImageUrl())
//                .category(categoryService.getCategoryById(productDTO.getCategory().getId()))
//                .productOptionValues(productOptionValueService.convertFromDTO(productDTO.getProductOptionValues()))
//                .cartItems(productDTO.getCartItemsIds().stream().map(cartItemService::getCartItemById).toList())
//                .build();
//    }
//
//    public static List<Product> convertFromDTO(List<ProductDTO> productDTOs) {
//        if (productDTOs == null || productDTOs.isEmpty()) {
//            return Collections.emptyList();
//        }
//        return productDTOs.stream()
//                .map(this::convertFromDTO)
//                .toList();
//    }

}
