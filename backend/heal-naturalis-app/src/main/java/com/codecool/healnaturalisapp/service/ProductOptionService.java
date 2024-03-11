package com.codecool.healnaturalisapp.service;

import com.codecool.healnaturalisapp.dto.ProductOptionDTO;
import com.codecool.healnaturalisapp.mapper.ProductOptionMapper;
import com.codecool.healnaturalisapp.model.ProductOption;
import com.codecool.healnaturalisapp.repository.ProductOptionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductOptionService {

    private final ProductOptionRepository productOptionRepository;

    private final ProductOptionMapper productOptionMapper;

    public boolean existsById(long id) {
        return productOptionRepository.existsById(id);
    }

    public boolean existsByName(String name) {
        return productOptionRepository.existsByName(name);
    }

    public ProductOption getProductOptionByName(String name) {
        return productOptionRepository.findByName(name);
    }

    public ProductOption getProductOptionById(long id) {
        return productOptionRepository.findById(id).orElse(null);
    }

    public ProductOptionDTO getProductOptionDTOById(long id) {
        if (!existsById(id)) throw new EntityNotFoundException("No product option found with ID " + id);
        return productOptionMapper.convertToDTO(getProductOptionById(id));
    }

    public void saveProductOption(ProductOptionDTO productOptionDTO) {
        ProductOption convertedProductOption = productOptionMapper.convertFromDTO(productOptionDTO);
        productOptionRepository.save(convertedProductOption);
    }

}
