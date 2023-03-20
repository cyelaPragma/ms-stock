package com.acelera.ti.stock.mock.stock;

import com.acelera.ti.stock.domain.model.model.stock.Stock;
import com.acelera.ti.stock.mock.product.ProductMocks;

import java.util.ArrayList;
import java.util.List;

public class StockMocks {
    public static Stock getStock(Long id) {
        return Stock.builder()
                .id(id)
                .product(ProductMocks.getProduct(id))
                .amount(10)
                .sellprice(10000.00)
                .build();
    }

    public static List<Stock> getStocks(int size) {
        List<Stock> stocks = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            stocks.add(getStock((long) i));
        }
        return stocks;
    }
}
