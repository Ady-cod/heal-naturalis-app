package com.codecool.healnaturalisapp.mapper;

import com.codecool.healnaturalisapp.dto.ProductDTO;
import com.codecool.healnaturalisapp.model.CartItem;
import com.codecool.healnaturalisapp.model.Product;
import com.codecool.healnaturalisapp.repository.CartItemRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, ProductOptionValueMapper.class})
public abstract class ProductMapper {

    @Autowired
    protected CartItemRepository cartItemRepository;

    @Mapping(target = "cartItemsIds", source = "cartItems")
    public abstract ProductDTO convertToDTO(Product product);

    protected List<Long> convertCartItemsToIds(List<CartItem> cartItems) {
        if (cartItems == null || cartItems.isEmpty()) {
            return new ArrayList<>();
        }
        return cartItems.stream()
                .map(CartItem::getId)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public abstract List<ProductDTO> convertToDTO(List<Product> products);

    @Mapping(target = "cartItems", source = "cartItemsIds")
    public abstract Product convertFromDTO(ProductDTO productDTO);

    protected List<CartItem> convertIdsToCartItems(List<Long> idList) {
        if (idList == null || idList.isEmpty()) {
            return new ArrayList<>();
        }
        return cartItemRepository.findAllById(idList);
    }

    public abstract List<Product> convertFromDTO(List<ProductDTO> productDTOs);

}
