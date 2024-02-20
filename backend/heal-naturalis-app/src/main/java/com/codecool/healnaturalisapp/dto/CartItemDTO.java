package com.codecool.healnaturalisapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {

        private long id;

        private int quantity;

        private ProductDTO product;

        private long cartId;

}
