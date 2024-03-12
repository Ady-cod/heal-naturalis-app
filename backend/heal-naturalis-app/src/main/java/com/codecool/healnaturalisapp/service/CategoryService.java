package com.codecool.healnaturalisapp.service;

import com.codecool.healnaturalisapp.dto.CategoryDTO;
import com.codecool.healnaturalisapp.mapper.CategoryMapper;
import com.codecool.healnaturalisapp.model.Category;
import com.codecool.healnaturalisapp.model.ProductOption;
import com.codecool.healnaturalisapp.repository.CategoryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
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

    private final CategoryMapper categoryMapper;

    private final ProductOptionService productOptionService;

    @PersistenceContext
    private final EntityManager entityManager;

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

}
