package com.codecool.healnaturalisapp.repository;

import com.codecool.healnaturalisapp.model.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {
    List<ProductOption> findAllByProducts_Id(long productId);

    ProductOption getProductOptionById(long productOptionId);
}
