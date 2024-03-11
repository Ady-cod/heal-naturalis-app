package com.codecool.healnaturalisapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "product_option_values")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductOptionValue {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Column(unique = true)
    private String value;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn
    private ProductOption productOption;

    @ManyToMany(mappedBy = "productOptionValues", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Product> products;
}
