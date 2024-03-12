package com.codecool.healnaturalisapp.repository;

import com.codecool.healnaturalisapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByParentCategoryIsNull();
    List<Category> findAllByParentCategory_Id(long id);

    boolean existsByName(String name);
}
