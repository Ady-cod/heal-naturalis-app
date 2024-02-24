package com.codecool.healnaturalisapp.service;

import com.codecool.healnaturalisapp.dto.CategoryDTO;
import com.codecool.healnaturalisapp.model.Category;
import com.codecool.healnaturalisapp.model.ProductOption;
import com.codecool.healnaturalisapp.repository.CategoryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final ProductOptionService productOptionService;

    @PersistenceContext
    final EntityManager entityManager;

    public List<CategoryDTO> getAllMainCategories() {
        List<Category> mainCategories = categoryRepository.findAllByParentCategoryIsNull();
        return this.convertToDTO(mainCategories);
    }

    public List<CategoryDTO> getAllSubCategories(long parentId) {
        List<Category> subcategories = categoryRepository.findAllByParentCategory_Id(parentId);
        return this.convertToDTO(subcategories);
    }

    public Category getCategoryById(long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Transactional
    public void addCategory(CategoryDTO categoryDTO) {
        Category categoryToSave = this.convertFromDTO(categoryDTO);
        categoryRepository.save(categoryToSave);
    }

    public List<CategoryDTO> convertToDTO(List<Category> categories) {
        return categories.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public CategoryDTO convertToDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .imageUrl(category.getImageUrl())
                .parentCategoryId(category.getParentCategory().getId())
                .subCategoryIds(category.getSubCategories().stream().map(Category::getId).toList())
                .productOptions(productOptionService.convertToDTO(category.getProductOptions()))
                .build();
    }

    @Transactional
    public Category convertFromDTO(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            return null;
        }
        Category convertedCategory = Category.builder()
                .id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .imageUrl(categoryDTO.getImageUrl())
                .parentCategory(categoryRepository.getCategoryById(categoryDTO.getParentCategoryId()))
                .subCategories(categoryRepository.findAllById(categoryDTO.getSubCategoryIds()))
                .productOptions(productOptionService.convertFromDTO(categoryDTO.getProductOptions()))
                .build();

        if ( ! convertedCategory.getProductOptions().isEmpty()) {
            List<ProductOption> managedOptions = new ArrayList<>();
            for (ProductOption option : convertedCategory.getProductOptions()) {
                if (option != null && productOptionService.getProductOptionById(option.getId()) != null) {
                    ProductOption managedOption = entityManager.merge(option);
                    managedOptions.add(managedOption);
                } else {
                    managedOptions.add(option);
            }
        }
        convertedCategory.setProductOptions(managedOptions);
        }
//        Category savedConvertedCategory = categoryRepository.save(convertedCategory);
//
//        // Use a map to track unique product options by name
//        Map<String, ProductOption> nameToProductOptionMap = new HashMap<>();
//
//        // Add product options from the parent category, if any
//        if (savedConvertedCategory.getParentCategory() != null
//                && !savedConvertedCategory.getParentCategory().getProductOptions().isEmpty()) {
//            for (ProductOption option : savedConvertedCategory.getParentCategory().getProductOptions()) {
//                nameToProductOptionMap.putIfAbsent(option.getName(), option);
//            }
//        }
//
//        // Add product options from DTO, if any
//        if (! productOptionService.convertFromDTO(categoryDTO.getProductOptions()).isEmpty()) {
//        for (ProductOption option : productOptionService.convertFromDTO(categoryDTO.getProductOptions())) {
//            nameToProductOptionMap.putIfAbsent(option.getName(), option);
//        }
//        }
//
//        // Convert the values of the map back to a list
//        List<ProductOption> productOptionsToBeAdded = new ArrayList<>(nameToProductOptionMap.values());
//
//        savedConvertedCategory.setProductOptions(productOptionsToBeAdded);
//        return savedConvertedCategory;
        return convertedCategory;
    }
}
