package com.acelera.ti.stock.domain.usecase;

import com.acelera.ti.stock.domain.model.exceptions.NotExistStocksException;
import com.acelera.ti.stock.domain.model.gateways.repositories.StockRepository;
import com.acelera.ti.stock.domain.model.model.stock.Stock;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public class GetStocksForSaleUseCase {
    private final StockRepository stockRepository;
    public List<Stock> action(){
        List<Stock> stocks = stockRepository.findByAmountGreaterThanAndSellPriceIsNotNull();
        if (stocks == null || stocks.isEmpty()) {
            throw new NotExistStocksException();
        }
        return stocks;
    }
}