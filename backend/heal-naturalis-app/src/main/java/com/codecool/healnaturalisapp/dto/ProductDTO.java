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
public class ProductDTO {

    private long id;

    private int stock;

    private double price;

    private String imageUrl;

    private CategoryDTO category;

    private List<ProductOptionValueDTO> productOptionValues = new ArrayList<>();

    private List<Long> cartItemsIds;

}
