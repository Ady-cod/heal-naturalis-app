package com.codecool.healnaturalisapp.config;

import com.codecool.healnaturalisapp.service.CategoryService;
import com.codecool.healnaturalisapp.service.ProductService;
import com.codecool.healnaturalisapp.service.TherapyService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final TherapyService therapyService;

    private final CategoryService categoryService;

    private final ProductService productService;

    @Override
    public void run(String... args) {
            if (therapyService.countTherapies() == 0) {
                therapyService.populateTherapies();
            }
            if (categoryService.countCategories() == 0) {
                categoryService.populateMainCategories();
            }
            if (productService.countProducts() == 0) {
                productService.populateProducts();
            }
    }


}
