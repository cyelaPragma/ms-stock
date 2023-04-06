package com.acelera.ti.stock.mock.stock;

import com.acelera.ti.stock.domain.model.model.stock.ProvisioningProduct;

import java.util.ArrayList;
import java.util.List;

public class ProvisioningProductMocks {
    public static ProvisioningProduct getProvisioningProduct(Long id) {
        return ProvisioningProduct.builder()
                .id(id)
                .providerPrice(25000.00)
                .amount(3)
                .stock(StockMocks.getStock(id))
                .build();
    }

    public static List<ProvisioningProduct> getProvisioningProducts(int size) {
        List<ProvisioningProduct> provisioningProducts = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            provisioningProducts.add(getProvisioningProduct((long) i));
        }
        return provisioningProducts;
    }
}
