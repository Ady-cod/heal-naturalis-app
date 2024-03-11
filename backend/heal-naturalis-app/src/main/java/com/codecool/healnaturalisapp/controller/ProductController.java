package com.codecool.healnaturalisapp.controller;

import com.codecool.healnaturalisapp.dto.ProductDTO;
import com.codecool.healnaturalisapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all-by-category/{categoryId}")
    public ResponseEntity<List<ProductDTO>> getAllProductsByCategoryId(@PathVariable long categoryId) {
        List<ProductDTO> retrievedProductDTOs = productService.getAllProductDTOsByCategoryId(categoryId);
        return ResponseEntity.ok(retrievedProductDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable long id) {
        ProductDTO retrievedProductDTO = productService.getProductDTOById(id);
        return ResponseEntity.ok(retrievedProductDTO);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addProduct(@RequestBody ProductDTO productDTO) {
        productService.saveProduct(productDTO);
        return ResponseEntity.ok().build();
    }
}
