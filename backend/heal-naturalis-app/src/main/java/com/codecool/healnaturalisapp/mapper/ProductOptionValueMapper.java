package com.codecool.healnaturalisapp.mapper;

import com.codecool.healnaturalisapp.dto.ProductOptionValueDTO;
import com.codecool.healnaturalisapp.model.ProductOptionValue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductOptionMapper.class})
public interface ProductOptionValueMapper {

    ProductOptionValueDTO convertToDTO(ProductOptionValue productOptionValue);
    List<ProductOptionValueDTO> convertToDTO(List<ProductOptionValue> productOptionValues);

    @Mapping(target = "products", ignore = true)
    ProductOptionValue convertFromDTO(ProductOptionValueDTO productOptionValueDTO);

    List<ProductOptionValue> convertFromDTO(List<ProductOptionValueDTO> productOptionValueDTOs);
}
