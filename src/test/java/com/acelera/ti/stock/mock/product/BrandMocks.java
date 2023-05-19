package com.acelera.ti.stock.mock.product;

import com.acelera.ti.stock.domain.model.model.product.Brand;

import java.util.ArrayList;
import java.util.List;

public class BrandMocks {
    public static Brand getBrand(Long id) {
        return Brand.builder()
                .id(id)
                .name("marca" + id)
                .description("descripción " + id)
                .build();
    }

    public static List<Brand> getBrands(int start, int end) {
        List<Brand> brands = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            brands.add(getBrand((long) i));
        }
        return brands;
    }
}
