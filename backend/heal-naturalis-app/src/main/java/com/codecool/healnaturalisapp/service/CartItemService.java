package com.codecool.healnaturalisapp.service;

import com.codecool.healnaturalisapp.model.CartItem;
import com.codecool.healnaturalisapp.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    public CartItem getCartItemById(long cartItemId) {
        return cartItemRepository.findById(cartItemId).orElse(null);
    }
}
