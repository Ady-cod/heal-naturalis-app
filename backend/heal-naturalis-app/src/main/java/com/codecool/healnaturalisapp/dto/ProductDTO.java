package com.codecool.healnaturalisapp.dto;

import com.codecool.healnaturalisapp.model.CartItem;
import com.codecool.healnaturalisapp.model.Product;
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

    private long categoryId;

    private String categoryName;

    private List<ProductOptionDTO> productOptions = new ArrayList<>();

    private List<Long> cartItemsIds;

}
