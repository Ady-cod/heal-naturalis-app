package com.codecool.healnaturalisapp.service;

import com.codecool.healnaturalisapp.dto.ProductDTO;
import com.codecool.healnaturalisapp.mapper.ProductMapper;
import com.codecool.healnaturalisapp.model.Category;
import com.codecool.healnaturalisapp.model.Product;
import com.codecool.healnaturalisapp.model.ProductOption;
import com.codecool.healnaturalisapp.model.ProductOptionValue;
import com.codecool.healnaturalisapp.repository.ProductRepository;
import com.codecool.healnaturalisapp.util.JsonReader;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final ProductOptionService productOptionService;

    private final ProductOptionValueService productOptionValueService;

    private final CategoryService categoryService;

    @PersistenceContext
    private final EntityManager entityManager;

    private final JsonReader jsonReader;

    @Value("classpath:data/products.json")
    private Resource productsResource;

    @Value("${application.multiplier}")
    private int multiplier;

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public List<ProductDTO> getAllProductDTOsByCategoryId(long categoryId) {
        if (!categoryService.existsById(categoryId)) {
            throw new EntityNotFoundException("Category with ID " + categoryId + " does not exist!");
        }
        List<Product> retrievedProducts = productRepository.findAllByCategory_Id(categoryId);
        return productMapper.convertToDTO(retrievedProducts);
    }

    public ProductDTO getProductDTOById(long productId) {
        Product retrievedProduct = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product with ID " + productId + " does not exist!"));
        return productMapper.convertToDTO(retrievedProduct);
    }

    @Transactional
    public void saveProduct(ProductDTO productDTO) {
        Product convertedProduct = productMapper.convertFromDTO(productDTO);
        syncProductOptions(convertedProduct);
        setPersistenceContext(convertedProduct);
        productRepository.save(convertedProduct);
    }

    private void syncProductOptions(Product product) {
        Category category = product.getCategory();
        List<ProductOptionValue> productOptionValues = product.getProductOptionValues();

        boolean hasCategoryOptions = category != null && category.getProductOptions() != null &&
                !category.getProductOptions().isEmpty();
        boolean hasProductOptionValues = productOptionValues != null && !productOptionValues.isEmpty();

        if (!hasCategoryOptions || !hasProductOptionValues) {
            return;
        }
        productOptionValues.stream()
                .filter(productOptionValue -> productOptionValue != null && productOptionValue.getProductOption() != null)
                .forEach(productOptionValue -> coordinateCategoryAndValueOptions(productOptionValue, category));
    }

    private void coordinateCategoryAndValueOptions(ProductOptionValue productOptionValue, Category category) {
        ProductOption optionForValue = productOptionValue.getProductOption();
        String optionName = optionForValue.getName();

        // Attempt to find an existing option by name within the category
        Optional<ProductOption> existingCategoryOption = category.getProductOptions().stream()
                .filter(option -> option.getName().equals(optionName))
                .findFirst();

        // Retrieve the option by name from the database
        ProductOption optionWithNameInDatabase = productOptionService.getProductOptionByName(optionName);

        ProductOption optionToUse;

        if (existingCategoryOption.isEmpty()) {
            // Use the option from the database if it exists; otherwise, merge the new one
            optionToUse = optionWithNameInDatabase == null ? entityManager.merge(optionForValue) : optionWithNameInDatabase;
            category.addProductOption(optionToUse);
        } else {
            ProductOption optionForCategory = existingCategoryOption.get();

            if (optionWithNameInDatabase != null && optionWithNameInDatabase.getId() != optionForCategory.getId()) {
                // If the existing category option doesn't match the one in the database by ID, update the category's option list
                category.getProductOptions().remove(optionForCategory);
                optionToUse = optionWithNameInDatabase.getId() == optionForValue.getId() ? entityManager.merge(optionForValue) : optionWithNameInDatabase;
                category.addProductOption(optionToUse);
            } else {
                optionToUse = optionForCategory;
            }
        }

        // Associate the ProductOptionValue with the determined ProductOption
        productOptionValue.setProductOption(optionToUse);
    }

    private void setPersistenceContext(Product product) {
        if (product != null) {
            if (product.getCategory() != null) {
                Category category = product.getCategory();
                categoryService.setPersistenceContext(category);
                if (category.getId() != 0 && categoryService.existsById(category.getId())) {
                    Category managedCategory = entityManager.merge(category);
                    product.setCategory(managedCategory);
                }
            }
            if (product.getProductOptionValues() != null && !product.getProductOptionValues().isEmpty()) {
                List<ProductOptionValue> managedProductOptionValues = new ArrayList<>();
                for (ProductOptionValue productOptionValue : product.getProductOptionValues()) {
                    productOptionValueService.setPersistenceContext(productOptionValue);
                    if (productOptionValue.getId() != 0 &&
                            productOptionValueService.existsById(productOptionValue.getId())) {
                        ProductOptionValue managedProductOptionValue = entityManager.merge(productOptionValue);
                        managedProductOptionValues.add(managedProductOptionValue);
                    } else {
                        managedProductOptionValues.add(productOptionValue);
                    }
                }
                product.setProductOptionValues(managedProductOptionValues);
            }
        }
    }

    public long countProducts() {
        return productRepository.count();
    }

    private List<ProductDTO> readProductsFromJson() {
        List<ProductDTO> originalProducts = jsonReader.readJsonList(productsResource, new TypeReference<>() {
        });
        if (originalProducts == null || originalProducts.isEmpty()) {
            logger.warn("No products were found in the JSON file!");
            return new ArrayList<>();
        }
        return originalProducts;
    }

    private boolean validateProductsList(List<ProductDTO> products) {
        if (products == null || products.contains(null)) {
            logger.warn("Products list is null or contains null values!");
            return false;
        }
        return true;
    }

    private List<ProductDTO> multiplyAndModifyProducts(List<ProductDTO> originalProducts) {
        List<ProductDTO> products = new ArrayList<>();
        // we create a random value between 0-100 including 0 and 100 to add for the stock and price
        try {
            for (int i = 0; i < multiplier; i++) {
                int randomStockValue = (int) (Math.random() * 99)+1;
                double randomPriceValue = (double) Math.round((Math.random() * 99 + 1) * 100) /100;
                for (ProductDTO originalProduct : originalProducts) {
                    ProductDTO product = ProductDTO.builder()
                            .stock(originalProduct.getStock() + randomStockValue)
                            .price(originalProduct.getPrice() + randomPriceValue)
                            .imageUrl(originalProduct.getImageUrl())
                            .category(originalProduct.getCategory())
                            .productOptionValues(originalProduct.getProductOptionValues())
                            .build();
                    products.add(product);
                }
            }
        } catch (NullPointerException | IndexOutOfBoundsException | ArithmeticException e) {
            logger.error("Error occurred while modifying and multiplying products", e);
            throw e;
        }
        return products;
    }

    private void saveProcessedProductsFromJson(List<ProductDTO> products) {
        List<Product> convertedProducts = productMapper.convertFromDTO(products);
        for (Product product : convertedProducts) {
            Category category = product.getCategory();
            List<ProductOptionValue> productOptionValues = product.getProductOptionValues();
            List<ProductOptionValue> managedProductOptionValues = new ArrayList<>();
            Category managedCategory;

            for (ProductOptionValue productOptionValue : productOptionValues) {
                ProductOption optionForValue = productOptionValue.getProductOption();
                ProductOption optionToBeShared;
                ProductOptionValue managedProductOptionValue;
                ProductOption managedOption;

                if (!categoryService.existsByName(category.getName())) {
                    if (productOptionService.existsByName(optionForValue.getName())) {
                        ProductOption existingOption = productOptionService.getProductOptionByName(optionForValue.getName());
                        optionToBeShared = entityManager.merge(existingOption);
                    } else {
                        optionToBeShared = entityManager.merge(optionForValue);
                    }
                    category.addProductOption(optionToBeShared);
                }

                if (productOptionValueService.existsByValue(productOptionValue.getValue())) {
                    ProductOptionValue existingProductOptionValue = productOptionValueService.getProductOptionValueByValue(productOptionValue.getValue());
                    managedProductOptionValue = entityManager.merge(existingProductOptionValue);
                    managedProductOptionValues.add(managedProductOptionValue);
                } else {
                    if (productOptionService.existsByName(optionForValue.getName())) {
                        ProductOption existingOption = productOptionService.getProductOptionByName(optionForValue.getName());
                        managedOption = entityManager.merge(existingOption);
                    } else {
                        managedOption = entityManager.merge(optionForValue);
                    }
                    productOptionValue.setProductOption(managedOption);
                    managedProductOptionValue = entityManager.merge(productOptionValue);
                    managedProductOptionValues.add(managedProductOptionValue);
                }
            }

            if (categoryService.existsByName(category.getName())) {
                Category existingCategory = categoryService.getCategoryByName(category.getName());
                managedCategory = entityManager.merge(existingCategory);
            } else {
                managedCategory = entityManager.merge(category);
            }
            product.setCategory(managedCategory);
            product.setProductOptionValues(managedProductOptionValues);
        }
        productRepository.saveAll(convertedProducts);
    }

    @Transactional
    public void populateProducts() {
        List<ProductDTO> originalProducts = readProductsFromJson();
        if (!validateProductsList(originalProducts)) {
            logger.warn("Products list is invalid, not saving to database!");
            return;
        }
        List<ProductDTO> modifiedProducts = multiplyAndModifyProducts(originalProducts);
        saveProcessedProductsFromJson(modifiedProducts);
    }

}
