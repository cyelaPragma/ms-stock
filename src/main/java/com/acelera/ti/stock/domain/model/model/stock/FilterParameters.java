package com.acelera.ti.stock.domain.model.model.stock;

import com.acelera.ti.stock.domain.model.model.product.Brand;
import com.acelera.ti.stock.domain.model.model.product.Category;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
@Getter
@EqualsAndHashCode
public class FilterParameters {
    private List<Brand> brand;
    private List<Category> category;
    private Double maxPrice;
    private Double minPrice;
}
