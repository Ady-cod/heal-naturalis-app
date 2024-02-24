package com.codecool.healnaturalisapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_options")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductOption {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "productOptions", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "productOption", cascade = CascadeType.ALL)
    private List<ProductOptionValue> productOptionValues = new ArrayList<>();

}
