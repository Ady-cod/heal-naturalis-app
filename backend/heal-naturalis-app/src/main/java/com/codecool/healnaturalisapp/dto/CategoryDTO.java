package com.codecool.healnaturalisapp.dto;

import com.codecool.healnaturalisapp.model.Category;
import com.codecool.healnaturalisapp.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private long id;

    private String name;

    private String imageUrl;

    private long parentCategoryId;

    private List<Long> subCategoryIds;

    private List<Long> productIds;

    public static List<CategoryDTO> convertToDTO(List<Category> categories) {
        return categories.stream()
                .map(CategoryDTO::convertToDTO)
                .toList();
    }

    public static CategoryDTO convertToDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .imageUrl(category.getImageUrl())
                .parentCategoryId(category.getParentCategory().getId())
                .subCategoryIds(category.getSubCategories().stream().map(Category::getId).toList())
                .productIds(category.getProducts().stream().map(Product::getId).toList())
                .build();
    }
}
