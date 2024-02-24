package com.codecool.healnaturalisapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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

    private List<Long> subCategoryIds = new ArrayList<>();

    private List<ProductOptionDTO> productOptions = new ArrayList<>();

}
