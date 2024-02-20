package com.codecool.healnaturalisapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_items")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue
    private long id;

    private int quantity;

    @ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn
    private Product product;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn
    private Cart cart;
}
