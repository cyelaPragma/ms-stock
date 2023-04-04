package com.acelera.ti.stock.domain.model.model.parameters;

import com.acelera.ti.stock.domain.model.model.product.Brand;
import com.acelera.ti.stock.domain.model.model.product.Category;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Builder
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class FilterParameters {
    private List<Brand> brand;
    private List<Category> category;
    private Double maxPrice;
    private Double minPrice;
}
