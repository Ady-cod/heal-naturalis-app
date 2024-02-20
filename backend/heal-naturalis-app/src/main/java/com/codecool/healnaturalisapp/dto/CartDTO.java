package com.codecool.healnaturalisapp.dto;

import com.codecool.healnaturalisapp.model.CartStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

        private long id;

        private CartStatus status;

        private List<CartItemDTO> cartItems;

        private UserDTO user;

        private LocalDateTime checkoutDate;

}