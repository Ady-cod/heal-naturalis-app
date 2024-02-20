package com.codecool.healnaturalisapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String value;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn
    private ProductOption productOption;

}
