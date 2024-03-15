package com.codecool.healnaturalisapp.service;

import com.codecool.healnaturalisapp.dto.ProductOptionValueDTO;
import com.codecool.healnaturalisapp.mapper.ProductOptionValueMapper;
import com.codecool.healnaturalisapp.model.ProductOption;
import com.codecool.healnaturalisapp.model.ProductOptionValue;
import com.codecool.healnaturalisapp.repository.ProductOptionValueRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductOptionValueService {

    private final ProductOptionValueRepository productOptionValueRepository;

    private final ProductOptionService productOptionService;

    private final ProductOptionValueMapper productOptionValueMapper;

    @PersistenceContext
    private final EntityManager entityManager;

    public ProductOptionValueDTO getProductOptionValueById(long id) {
        ProductOptionValue retrievedProductOptionValue = productOptionValueRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Product option value with ID " + id + " does not exist!"));
        return productOptionValueMapper.convertToDTO(retrievedProductOptionValue);
    }

    public List<ProductOptionValueDTO> getAllProductOptionValuesByProductOptionId(long productOptionId) {
        List<ProductOptionValue> productOptionValues = productOptionValueRepository.findAllByProductOptionId(productOptionId);
        if (productOptionValues.isEmpty()) {
            throw new EntityNotFoundException("No product option values found for product option with ID " + productOptionId);
        }
        return productOptionValueMapper.convertToDTO(productOptionValues);
    }

    public boolean existsById(long id) {
        return productOptionValueRepository.existsById(id);
    }

    public boolean existsByValue(String value) {
        return productOptionValueRepository.existsByValue(value);
    }

    @Transactional
    public void saveProductOptionValue(ProductOptionValueDTO productOptionValueDTO) {
        ProductOptionValue convertedProductOptionValue = productOptionValueMapper.convertFromDTO(productOptionValueDTO);
        setPersistenceContext(convertedProductOptionValue);
        productOptionValueRepository.save(convertedProductOptionValue);
    }

    public void setPersistenceContext(ProductOptionValue productOptionValue) {
        if (productOptionValue != null && productOptionValue.getProductOption() != null) {
            ProductOption productOption = productOptionValue.getProductOption();
            if (productOption.getId() != 0 &&
                    productOptionService.existsById(productOption.getId())) {
                ProductOption managedProductOption = entityManager.merge(productOption);
                productOptionValue.setProductOption(managedProductOption);
            }
        }
    }

    public ProductOptionValue getProductOptionValueByValue(String value) {
        return productOptionValueRepository.findByValue(value);
    }
}
