package com.acelera.ti.stock.mock.stock;

import com.acelera.ti.stock.domain.model.model.stock.FilterParameters;
import com.acelera.ti.stock.mock.product.BrandMocks;
import com.acelera.ti.stock.mock.product.CategoryMocks;

public class FilterParametersMocks {
    public static FilterParameters getFilterParameters(){
        return FilterParameters.builder()
                .brand(BrandMocks.getBrands(2, 3))
                .category(CategoryMocks.getCategories(3, 5))
                .minPrice(1000.0)
                .maxPrice(1300.0)
                .build();
    }

    public static FilterParameters getFilterByPrice(Double minPrice, Double maxPrice){
        return FilterParameters.builder()
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .build();
    }

}
