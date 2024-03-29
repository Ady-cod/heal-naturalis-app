package com.codecool.healnaturalisapp.repository;

import com.codecool.healnaturalisapp.model.ProductOptionValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOptionValueRepository extends JpaRepository<ProductOptionValue, Long> {
    List<ProductOptionValue> findAllByProductOptionId(long productOptionId);

    boolean existsByValue(String value);

    ProductOptionValue findByValue(String value);
}
