package com.acelera.ti.stock.mock.stock;

import java.util.ArrayList;
import java.util.List;
import com.acelera.ti.stock.domain.model.model.parameters.FilterProductsForSaleParameters;

public class FilterProductsForSaleParametersMocks {
    public static FilterProductsForSaleParameters getFilterAllParameters(){
        return FilterProductsForSaleParameters.builder()
                .brandName(getListString("marca", 1, 3))
                .categoryName(getListString("categoria",1, 3))
                .productName(getListString("Producto ",1, 3))
                .build();
    }
    private static List<String> getListString(String mesagge, int start, int end) {
        List<String> listResponse = new ArrayList<>();
        for(int i = start; i <= end; i++){
            listResponse.add(mesagge + i);
        }
        return listResponse;
    }
    public static FilterProductsForSaleParameters getInvalidFilterParameters() {
        return FilterProductsForSaleParameters.builder()
                .brandName(getListString("marca no valida", 1, 2))
                .categoryName(getListString("categoria no valida",1, 2))
                .productName(getListString("Producto no valido",1, 2))
                .build();
    }
    public static FilterProductsForSaleParameters getFilterByProductName() {
        return FilterProductsForSaleParameters.builder()
                .productName(getListString("Producto ",4, 7))
                .build();
    }
    public static FilterProductsForSaleParameters getFilterByCategoryName() {
        return FilterProductsForSaleParameters.builder()
                .categoryName(getListString("categoria",3, 8))
                .build();
    }
    public static FilterProductsForSaleParameters getFilterByBrandName() {
        return FilterProductsForSaleParameters.builder()
                .brandName(getListString("marca",4, 10))
                .build();
    }
    public static FilterProductsForSaleParameters getFilterByBrandAndProductName() {
        return FilterProductsForSaleParameters.builder()
                .productName(getListString("Producto ",4,10))
                .brandName(getListString("marca",4, 10))
                .build();
    }
    public static FilterProductsForSaleParameters getFilterByBrandAndCategoryName() {
        return FilterProductsForSaleParameters.builder()
                .categoryName(getListString("categoria",3,7))
                .brandName(getListString("marca",3,7))
                .build();
    }
    public static FilterProductsForSaleParameters getFilterByProductAndCategoryName() {
        return FilterProductsForSaleParameters.builder()
                .categoryName(getListString("categoria",1,7))
                .productName(getListString("Producto ",1,7))
                .build();
    }
}
