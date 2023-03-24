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
                .sellPrice(10000.00 + id)
                .build();
    }

    public static Stock getStockEmpty(Long id) {
        return Stock.builder()
                .id(id)
                .product(ProductMocks.getProduct(id))
                .amount(0)
                .sellPrice(10000.00)
                .build();
    }

    public static List<Stock> getStocks(int size) {
        List<Stock> stocks = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            stocks.add(getStock((long) i));
        }
        return stocks;
    }

    public static List<Stock> getStockFilters() {
        List<Stock> stocks = new ArrayList<>();
        stocks.add(
                Stock.builder()
                        .id(1L)
                        .amount(10)
                        .sellPrice(1000.00)
                        .product(ProductMocks.getProduct(1L))
                        .build()
        );
        stocks.add(
                Stock.builder()
                        .id(2L)
                        .amount(10)
                        .sellPrice(1100.00)
                        .product(ProductMocks.getProduct(2L))
                        .build()
        );
        stocks.add(
                Stock.builder()
                        .id(3L)
                        .amount(10)
                        .sellPrice(1200.00)
                        .product(ProductMocks.getProduct(3L))
                        .build()
        );

        return stocks;
    }
}

