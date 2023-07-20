package com.codecool.healnaturalisapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "service")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProvidedTherapyService {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    private String description;

    @Column(length = 2048)
    private String imageUrl;

    private BigDecimal price;

    private String schedule;

    private String therapistName;

    private String phoneNumber;

}
