package com.codecool.healnaturalisapp.repository.projections;

import com.codecool.healnaturalisapp.model.Product;
import com.codecool.healnaturalisapp.model.ProductOptionValue;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "withProductOptionValues", types = { Product.class })
public interface ProductForSaleProjection {
    Long getId();
    Integer getStock();
    Double getPrice();
    List<ProductOptionValue> getProductOptionValues();
}
