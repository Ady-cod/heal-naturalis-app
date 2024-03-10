package com.codecool.healnaturalisapp.mapper;

import com.codecool.healnaturalisapp.dto.ProductOptionDTO;
import com.codecool.healnaturalisapp.model.ProductOption;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductOptionMapper {
    ProductOptionDTO convertToDTO(ProductOption productOption);

    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "productOptionValues", ignore = true)
    ProductOption convertFromDTO(ProductOptionDTO productOptionDTO);
}
