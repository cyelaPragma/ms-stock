package com.acelera.ti.stock.mock.product;

import com.acelera.ti.stock.domain.model.model.product.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryMocks {
    public static Category getCategory(Long id) {
        return Category.builder()
                .id(id)
                .name("categoria" + id)
                .description("descripción " + id)
                .build();
    }

    public static List<Category> getCategories(int start, int end) {
        List<Category> categories = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            categories.add(getCategory((long) i));
        }
        return categories;
    }
}
