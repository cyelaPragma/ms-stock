package com.acelera.ti.stock.mock.product;

import com.acelera.ti.stock.domain.model.model.product.Category;

public class CategoryMocks {
    public static Category getCategory(Long id) {
        return Category.builder()
                .id(id)
                .name("categoria " + id)
                .description("descripción " + id)
                .build();
    }
}
