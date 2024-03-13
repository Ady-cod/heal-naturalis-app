package com.codecool.healnaturalisapp.mapper;

import com.codecool.healnaturalisapp.dto.CategoryDTO;
import com.codecool.healnaturalisapp.model.Category;
import com.codecool.healnaturalisapp.repository.CategoryRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {ProductOptionMapper.class})
public abstract class CategoryMapper {

    @Autowired
    protected CategoryRepository categoryRepository;

    @Mapping(target = "subCategoryIds", source = "subCategories")
    @Mapping(target = "parentCategoryId", source = "parentCategory.id")
    public abstract CategoryDTO convertToDTO(Category category);

    protected List<Long> convertCategoriesToIds(List<Category> categories) {
        if (categories == null || categories.isEmpty()) {
            return new ArrayList<>();
        }
        return categories.stream()
                .map(Category::getId)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public abstract List<CategoryDTO> convertToDTO(List<Category> categories);

    @Mapping(target = "parentCategory", source = "parentCategoryId")
    @Mapping(target = "subCategories", source = "subCategoryIds")
    @Mapping(target = "products", ignore = true)
    public abstract Category convertFromDTO(CategoryDTO categoryDTO);

    protected Category convertIdToCategory(long categoryId) {
        if (categoryId == 0) {
            return null;
        }
        return categoryRepository.findById(categoryId).orElse(null);
    }

    protected List<Category> convertIdsToCategories(List<Long> categoryIds) {
        if (categoryIds == null || categoryIds.isEmpty()) {
            return new ArrayList<>();
        }
        return categoryRepository.findAllById(categoryIds);
    }

    public abstract List<Category> convertFromDTO(List<CategoryDTO> categoryDTOs);
}
