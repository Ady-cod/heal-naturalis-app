package com.codecool.healnaturalisapp.controller;

import com.codecool.healnaturalisapp.dto.ProductOptionDTO;
import com.codecool.healnaturalisapp.service.ProductOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product-option")
public class ProductOptionController {

    private final ProductOptionService productOptionService;

    @PostMapping("/add")
    public ResponseEntity<Void> saveProductOption(@RequestBody ProductOptionDTO productOptionDTO) {
        productOptionService.saveProductOption(productOptionDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductOptionDTO> getProductOptionById(@PathVariable long id) {
        ProductOptionDTO retrievedProductOption = productOptionService.getProductOptionDTOById(id);
        return ResponseEntity.ok(retrievedProductOption);
    }
}
