package com.codecool.healnaturalisapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductOptionDTO {

    private long id;

    private String name;

    private List<Long> productIds;

    private List<ProductOptionValueDTO> productOptionValues;

}
