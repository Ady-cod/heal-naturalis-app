package com.codecool.healnaturalisapp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private long id;

    @NotNull(message = "Category name cannot be null")
    private String name;

    @URL(message = "Invalid Category image URL format")
    private String imageUrl;

    private long parentCategoryId;

    private List<Long> subCategoryIds = new ArrayList<>();

    private List<ProductOptionDTO> productOptions = new ArrayList<>();

}
