package com.acelera.ti.stock.mock.stock;

import com.acelera.ti.stock.domain.model.model.stock.FilterParameters;
import com.acelera.ti.stock.mock.product.BrandMocks;
import com.acelera.ti.stock.mock.product.CategoryMocks;

public class FilterParametersMocks {
    public static FilterParameters getFilterAllParameters(){
        return FilterParameters.builder()
                .brand(BrandMocks.getBrands(2, 3))
                .category(CategoryMocks.getCategories(3, 5))
                .minPrice(10000.0)
                .maxPrice(10003.0)
                .build();
    }

    public static FilterParameters getFilterByPrice(Double minPrice, Double maxPrice){
        return FilterParameters.builder()
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .build();
    }


    public static FilterParameters getInvalidFilterParameters() {
        return FilterParameters.builder()
                .brand(BrandMocks.getBrands(5, 7))
                .category(CategoryMocks.getCategories(1, 2))
                .minPrice(100.0)
                .maxPrice(130.0)
                .build();
    }

    public static FilterParameters getFilterByBrand(int start, int end){
        return FilterParameters.builder()
                .brand(BrandMocks.getBrands(start, end))
                .build();
    }

    public static FilterParameters getFilterByCategory(int start, int end){
        return FilterParameters.builder()
                .category(CategoryMocks.getCategories(start, end))
                .build();
    }

    public static FilterParameters getFilterByBrandAndCategory(){
        return FilterParameters.builder()
                .category(CategoryMocks.getCategories(3,5))
                .brand((BrandMocks.getBrands(4,5)))
                .build();
    }

    public static FilterParameters getFilterByBrandAndPrice(Double minPrice, Double maxPrice){
        return FilterParameters.builder()
                .brand((BrandMocks.getBrands(1,5)))
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .build();
    }

    public static FilterParameters getFilterByPriceAndCategory(Double minPrice, Double maxPrice ){
        return FilterParameters.builder()
                .category((CategoryMocks.getCategories(7,7)))
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .build();
    }

}
