package com.codecool.healnaturalisapp.dto;

import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private String value;

    private ProductOptionDTO productOption;

}
