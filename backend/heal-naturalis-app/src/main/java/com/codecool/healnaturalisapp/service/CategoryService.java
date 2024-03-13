package com.codecool.healnaturalisapp.service;

import com.codecool.healnaturalisapp.dto.CategoryDTO;
import com.codecool.healnaturalisapp.mapper.CategoryMapper;
import com.codecool.healnaturalisapp.model.Category;
import com.codecool.healnaturalisapp.model.ProductOption;
import com.codecool.healnaturalisapp.repository.CategoryRepository;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final ProductOptionService productOptionService;

    private final CategoryMapper categoryMapper;

    @PersistenceContext
    private final EntityManager entityManager;

    private final JsonReader jsonReader;

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Value("classpath:data/products_main_categories.json")
    private Resource mainCategoriesResource;

    public List<CategoryDTO> getAllMainCategories() {
        List<Category> mainCategories = categoryRepository.findAllByParentCategoryIsNull();
        if (mainCategories.isEmpty()) {
            throw new EntityNotFoundException("No main categories found!");
        }
        return categoryMapper.convertToDTO(mainCategories);
    }

    public List<CategoryDTO> getAllSubCategories(long parentId) {
        List<Category> subcategories = categoryRepository.findAllByParentCategory_Id(parentId);
        if (subcategories.isEmpty()) {
            throw new EntityNotFoundException("No subcategories found for category with ID " + parentId);
        }
        return categoryMapper.convertToDTO(subcategories);
    }

    public boolean existsById(long id) {
        return categoryRepository.existsById(id);
    }

    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

    @Transactional
    public void saveCategory(CategoryDTO categoryDTO) {
        Category convertedCategory = categoryMapper.convertFromDTO(categoryDTO);

        setPersistenceContext(convertedCategory);

        categoryRepository.save(convertedCategory);
    }

    public void setPersistenceContext(Category category) {
        if (category != null ) {
            List<ProductOption> cumulatedProductOptionsWithParent = cumulateOptionsWithParent(category);
            List<ProductOption> managedProductOptions = manageProductOptions(cumulatedProductOptionsWithParent);
            category.setProductOptions(managedProductOptions);
        }
    }

    private List<ProductOption> cumulateOptionsWithParent(Category category) {

        // Use a map to track unique product options by name
        Map<String, ProductOption> nameToProductOptionMap = new HashMap<>();

        // Add product options from the parent category, if any
        if (category.getParentCategory() != null &&
                category.getParentCategory().getProductOptions() != null &&
                !category.getParentCategory().getProductOptions().isEmpty()) {
            for (ProductOption option : category.getParentCategory().getProductOptions()) {
                nameToProductOptionMap.putIfAbsent(option.getName(), option);
            }
        }

        // Add product options from category, if any
        if (category.getProductOptions() != null && !category.getProductOptions().isEmpty()) {
            for (ProductOption option : category.getProductOptions()) {
                nameToProductOptionMap.putIfAbsent(option.getName(), option);
            }
        }

        // Convert the values of the map back to a list
        return new ArrayList<>(nameToProductOptionMap.values());
    }

    private List<ProductOption> manageProductOptions(List<ProductOption> productOptions) {
        if (productOptions == null || productOptions.isEmpty()) {
            return new ArrayList<>();
        }
        List<ProductOption> managedOptions = new ArrayList<>();
        for (ProductOption option : productOptions) {
            if (option != null && option.getId() != 0 &&
                    productOptionService.existsById(option.getId())) {
                ProductOption managedOption = entityManager.merge(option);
                managedOptions.add(managedOption);
            } else {
                managedOptions.add(option);
            }
        }
        return managedOptions;
    }

    public long countCategories() {
        return categoryRepository.count();
    }

    private List<Category> readMainCategoriesFromJson() {
        List<Category> mainCategories = jsonReader.readJsonList(mainCategoriesResource, new TypeReference<>() {
        });
        if (mainCategories == null || mainCategories.isEmpty()) {
            logger.warn("No main categories found in the JSON file!");
            return new ArrayList<>();
        }
        return mainCategories;
    }

    private boolean validateMainCategoriesList(List<Category> mainCategories) {
        if (mainCategories == null || mainCategories.contains(null)) {
            logger.warn("Main categories list is null or contains null values!");
            return false;
        }
        return true;
    }

    public void populateMainCategories() {
        List<Category> mainCategories = readMainCategoriesFromJson();
        if (! validateMainCategoriesList(mainCategories)) {
            logger.warn("Main categories list is invalid, not saving to database!");
            return;
        }
        categoryRepository.saveAll(mainCategories);
    }

}
