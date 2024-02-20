package com.codecool.healnaturalisapp.controller;

import com.codecool.healnaturalisapp.dto.ProductDTO;
import com.codecool.healnaturalisapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products-by-category/{categoryId}")
    public ResponseEntity<List<ProductDTO>> getAllProductsByCategoryId(@PathVariable long categoryId) {
        System.out.println("getAllProducts() called");
        return ResponseEntity.ok(productService.getAllProductDTOsByCategoryId(categoryId));
    }

    @PostMapping("/add-product")
    public ResponseEntity<Void> addProduct(@RequestBody ProductDTO productDTO) {
        productService.addProduct(productDTO);
        return ResponseEntity.ok().build();
    }
}
