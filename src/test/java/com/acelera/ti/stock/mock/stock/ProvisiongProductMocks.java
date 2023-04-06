package com.acelera.ti.stock.mock.stock;

import com.acelera.ti.stock.domain.model.model.stock.ProvisiongProduct;

import java.util.ArrayList;
import java.util.List;

public class ProvisiongProductMocks {
    public static ProvisiongProduct getProvisiongProduct(Long id) {
        return ProvisiongProduct.builder()
                .id(id)
                .providerPrice(25000.00)
                .amount(3)
                .stock(StockMocks.getStock(id))
                .build();
    }

    public static List<ProvisiongProduct> getProvisiongProducts(int size) {
        List<ProvisiongProduct> provisiongProducts = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            provisiongProducts.add(getProvisiongProduct((long) i));
        }
        return provisiongProducts;
    }
}
