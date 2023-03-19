package com.acelera.ti.stock.mock.product;

import com.acelera.ti.stock.domain.model.model.product.Brand;

public class BrandMocks {
    public static Brand getBrand(Long id){
        return Brand.builder()
                .id(id)
                .name("marca "+id)
                .description("decripcion "+id)
                .build();
    }
}
