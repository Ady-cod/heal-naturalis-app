package com.codecool.healnaturalisapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductOptionValueDTO {

    private long id;

    private String value;

    private long productOptionId;

}
