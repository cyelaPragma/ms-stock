package com.acelera.ti.stock.mock.stock;

import com.acelera.ti.stock.domain.model.model.stock.ProvisiongProduct;

import java.util.ArrayList;
import java.util.List;

public class ProvisioningProductMocks {
    public static ProvisiongProduct getProvisioningProduct(Long id) {
        return ProvisiongProduct.builder()
                .id(id)
                .providerPrice(25000.00)
                .amount(3)
                .stock(StockMocks.getStock(id))
                .build();
    }

    public static List<ProvisiongProduct> getProvisioningProducts(int size) {
        List<ProvisiongProduct> provisioningProducts = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            provisioningProducts.add(getProvisioningProduct((long) i));
        }
        return provisioningProducts;
    }
}
