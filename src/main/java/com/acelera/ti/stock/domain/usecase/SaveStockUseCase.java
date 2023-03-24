package com.acelera.ti.stock.domain.usecase;

import com.acelera.ti.stock.domain.model.gateways.repositories.StockRepository;
import com.acelera.ti.stock.domain.model.model.stock.Stock;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SaveStockUseCase {
    private final StockRepository stockRepository;

    public Stock action(Stock stock) {
        return stockRepository.saveStock(stock);
    }
}
