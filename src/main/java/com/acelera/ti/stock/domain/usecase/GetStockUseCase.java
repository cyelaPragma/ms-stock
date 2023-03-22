package com.acelera.ti.stock.domain.usecase;

import com.acelera.ti.stock.domain.model.exceptions.StockEmptyException;
import com.acelera.ti.stock.domain.model.exceptions.StockNotFoundException;
import com.acelera.ti.stock.domain.model.gateways.repositories.StockRepository;
import com.acelera.ti.stock.domain.model.model.stock.Stock;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetStockUseCase {
    private final StockRepository stockRepository;
    public Stock action(Long id) {
        Stock stock = stockRepository.getStockById(id);
        if (stock == null) {
            throw new StockNotFoundException();
        }
        if (stock.getAmount() == 0) {
            throw new StockEmptyException();
        }
        return stock;
    }
}
