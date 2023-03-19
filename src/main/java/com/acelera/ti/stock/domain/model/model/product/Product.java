package com.acelera.ti.stock.domain.model.model.product;

import lombok.*;

@Builder
@Getter
@Setter
@EqualsAndHashCode
public class Product {
    private Long id;
    private String name;
    private String model;
    private Brand brand;
    private Category category;
    private String description;
}
