package com.acelera.ti.stock.mock.stock;

import com.acelera.ti.stock.domain.model.model.stock.Stock;
import com.acelera.ti.stock.mock.product.ProductMocks;

import java.util.ArrayList;
import java.util.Arrays;
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
        return Arrays.asList(
                Stock.builder().id(1L).product(ProductMocks.getProduct(1L)).sellPrice(10001.0).amount(10).build(),
                Stock.builder().id(2L).product(ProductMocks.getProduct(2L)).sellPrice(10002.0).amount(10).build(),
                Stock.builder().id(3L).product(ProductMocks.getProduct(3L)).sellPrice(10003.0).amount(10).build(),
                Stock.builder().id(4L).product(ProductMocks.getProduct(4L)).sellPrice(10004.0).amount(10).build(),
                Stock.builder().id(5L).product(ProductMocks.getProduct(5L)).sellPrice(10005.0).amount(10).build(),
                Stock.builder().id(6L).product(ProductMocks.getProduct(6L)).sellPrice(10006.0).amount(10).build(),
                Stock.builder().id(7L).product(ProductMocks.getProduct(7L)).sellPrice(10007.0).amount(10).build(),
                Stock.builder().id(8L).product(ProductMocks.getProduct(8L)).sellPrice(10008.0).amount(10).build(),
                Stock.builder().id(9L).product(ProductMocks.getProduct(9L)).sellPrice(10009.0).amount(10).build()

        );
    }
}

