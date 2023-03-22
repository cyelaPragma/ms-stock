package com.acelera.ti.stock.domain.usecase;

import com.acelera.ti.stock.domain.model.exceptions.NotExistProductsException;
import com.acelera.ti.stock.domain.model.gateways.repositories.StockRepository;
import com.acelera.ti.stock.domain.model.model.stock.Stock;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllStockUseCase {
    private final StockRepository stockRepository;

    public List<Stock> getAllStocks() {
        List<Stock> stocks = stockRepository.getAllStock();
        if (stocks.isEmpty()) {
            throw new NotExistProductsException();
        }
        return stocks;
    }
}
