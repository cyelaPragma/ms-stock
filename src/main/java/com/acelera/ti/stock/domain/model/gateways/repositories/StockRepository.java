package com.acelera.ti.stock.domain.model.gateways.repositories;

import com.acelera.ti.stock.domain.model.model.stock.Stock;

import java.util.List;

public interface
StockRepository {
    List<Stock> getAllStock();
    Stock getStockById(Long id);
    Stock saveStock(Stock stock);
}
