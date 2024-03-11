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
public class ProductOptionDTO {

    private long id;

    @NotNull(message = "Product option name cannot be null")
    private String name;

}
