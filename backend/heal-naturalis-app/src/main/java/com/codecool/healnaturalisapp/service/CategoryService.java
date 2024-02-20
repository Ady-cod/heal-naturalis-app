package com.codecool.healnaturalisapp.service;

import com.codecool.healnaturalisapp.dto.CategoryDTO;
import com.codecool.healnaturalisapp.model.Category;
import com.codecool.healnaturalisapp.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryDTO> getAllMainCategories() {
        List<Category> mainCategories = categoryRepository.findAllByParentCategoryIsNull();
        return CategoryDTO.convertToDTO(mainCategories);
    }
    public List<CategoryDTO> getAllSubCategories(long parentId) {
        List<Category> subcategories = categoryRepository.findAllByParentCategory_Id(parentId);
        return CategoryDTO.convertToDTO(subcategories);
    }
    public Category getCategoryById(long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public void addCategory (Category category) {
        categoryRepository.save(category);
    }
}
