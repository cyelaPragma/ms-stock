package com.acelera.ti.stock.infrastructure.drivenadapters.productservice.dto;

import com.acelera.ti.stock.domain.model.model.product.Brand;
import com.acelera.ti.stock.domain.model.model.product.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private String model;
    private Brand brand;
    private Category category;
    private String description;
}
