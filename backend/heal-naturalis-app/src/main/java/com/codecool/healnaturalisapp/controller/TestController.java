package com.codecool.healnaturalisapp.controller;

import com.codecool.healnaturalisapp.model.Category;
import com.codecool.healnaturalisapp.model.Product;
import com.codecool.healnaturalisapp.repository.CategoryRepository;
import com.codecool.healnaturalisapp.repository.ProductOptionRepository;
import com.codecool.healnaturalisapp.repository.ProductRepository;
import com.codecool.healnaturalisapp.repository.ProductOptionValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TestController {

    private final ProductRepository productRepository;
    private final ProductOptionValueRepository productOptionValueRepository;
    private final CategoryRepository categoryRepository;
    private final ProductOptionRepository productOptionRepository;

    @CrossOrigin(origins = {"http://localhost:3000", "http://192.168.1.165:3000", "http://192.168.1.139:3000"})
    @GetMapping("/testException")
    public String testException() {
        throw new RuntimeException("This is a test for handling exception!");
    }

    @CrossOrigin(origins = {"http://localhost:3000", "http://192.168.1.165:3000", "http://192.168.1.139:3000"})
    @GetMapping("/debug")
    public String debug() {

//        List<Category> categoryList = categoryRepository.findAll();
//        for (Category category : categoryList) {
//            if (category.getId() == 1) {
//                category.getProductOptions().add(productOptionRepository.findById(1L).get());
//                category.getProductOptions().add(productOptionRepository.findById(2L).get());
//                category.getProductOptions().add(productOptionRepository.findById(3L).get());
//                categoryRepository.save(category);
//            }
//        }
//        List<Product> productList = productRepository.findAll();
//        for (Product product : productList) {
//            if (product.getId() == 1) {
//                product.getProductOptionValues().add(productOptionValueRepository.findById(1L).get());
//                product.getProductOptionValues().add(productOptionValueRepository.findById(3L).get());
//                product.getProductOptionValues().add(productOptionValueRepository.findById(5L).get());
//                productRepository.save(product);
//            }
//            if (product.getId() == 2) {
//                product.getProductOptionValues().add(productOptionValueRepository.findById(2L).get());
//                product.getProductOptionValues().add(productOptionValueRepository.findById(4L).get());
//                product.getProductOptionValues().add(productOptionValueRepository.findById(5L).get());
//                productRepository.save(product);
//            }
//            if (product.getId() == 3) {
//                product.getProductOptionValues().add(productOptionValueRepository.findById(1L).get());
//                product.getProductOptionValues().add(productOptionValueRepository.findById(4L).get());
//                product.getProductOptionValues().add(productOptionValueRepository.findById(6L).get());
//                productRepository.save(product);
//            }
//        }
        return "debug";
    }
}
