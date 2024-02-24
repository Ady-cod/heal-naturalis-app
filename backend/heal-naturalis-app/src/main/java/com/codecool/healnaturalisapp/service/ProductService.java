package com.codecool.healnaturalisapp.service;

import com.codecool.healnaturalisapp.dto.ProductDTO;
import com.codecool.healnaturalisapp.model.CartItem;
import com.codecool.healnaturalisapp.model.Product;
import com.codecool.healnaturalisapp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
//    private final ProductOptionService productOptionService;
    private final CategoryService categoryService;
    private final CartItemService cartItemService;
    private final ProductOptionValueService productOptionValueService;

    public List<ProductDTO> getAllProductDTOsByCategoryId(long categoryId) {
        List<Product> products = productRepository.findAllByCategory_Id(categoryId);
        return this.convertToDTO(products);
    }

    public Product getProductById(long productOptionId) {
        return productRepository.findById(productOptionId).orElse(null);
    }
    public void addProduct(ProductDTO productDTO) {
        Product productToSave = this.convertFromDTO(productDTO);
        productRepository.save(productToSave);
    }
    public List<ProductDTO> convertToDTO(List<Product> products) {
        if (products == null || products.isEmpty()) {
            return Collections.emptyList();
        }
        return products.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public ProductDTO convertToDTO(Product product) {
        if (product == null) {
            return null;
        }
        return ProductDTO.builder()
                .id(product.getId())
                .stock(product.getStock())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .category(categoryService.convertToDTO(product.getCategory()))
                .productOptionValues(productOptionValueService.convertToDTO(product.getProductOptionValues()))
                .cartItemsIds(product.getCartItems().stream().map(CartItem::getId).toList())
                .build();
    }

    public Product convertFromDTO(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }
        return Product.builder()
                .id(productDTO.getId())
                .stock(productDTO.getStock())
                .price(productDTO.getPrice())
                .imageUrl(productDTO.getImageUrl())
                .category(categoryService.getCategoryById(productDTO.getCategory().getId()))
                .productOptionValues(productOptionValueService.convertFromDTO(productDTO.getProductOptionValues()))
                .cartItems(productDTO.getCartItemsIds().stream().map(cartItemService::getCartItemById).toList())
                .build();
    }

    public List<Product> convertFromDTO(List<ProductDTO> productDTOs) {
        if (productDTOs == null || productDTOs.isEmpty()) {
            return Collections.emptyList();
        }
        return productDTOs.stream()
                .map(this::convertFromDTO)
                .toList();
    }
}
