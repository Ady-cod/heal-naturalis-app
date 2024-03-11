package com.codecool.healnaturalisapp.service;

import com.codecool.healnaturalisapp.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
}
