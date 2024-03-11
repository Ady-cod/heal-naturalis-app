package com.codecool.healnaturalisapp.controller;

import com.codecool.healnaturalisapp.dto.ProductOptionValueDTO;
import com.codecool.healnaturalisapp.service.ProductOptionValueService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product-option-value")
public class ProductOptionValueController {

    private final ProductOptionValueService productOptionValueService;

    @PostMapping("/add")
    public ResponseEntity<Void> saveProductOptionValue(@RequestBody ProductOptionValueDTO productOptionValueDTO) {
        productOptionValueService.saveProductOptionValue(productOptionValueDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductOptionValueDTO> getProductOptionValueById(@PathVariable long id) {
        return ResponseEntity.ok(productOptionValueService.getProductOptionValueById(id));
    }

    @GetMapping("/all-by-product-option/{productOptionId}")
    public ResponseEntity<List<ProductOptionValueDTO>> getAllProductOptionValuesByProductOptionId(@PathVariable long productOptionId) {
        return ResponseEntity.ok(productOptionValueService.getAllProductOptionValuesByProductOptionId(productOptionId));
    }

}
