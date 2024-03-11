package com.codecool.healnaturalisapp.repository;

import com.codecool.healnaturalisapp.model.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {
    boolean existsByName(String name);

    ProductOption findByName(String name);
}
