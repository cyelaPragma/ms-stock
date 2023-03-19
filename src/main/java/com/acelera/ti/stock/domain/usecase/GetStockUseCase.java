package com.acelera.ti.stock.domain.usecase;

import com.acelera.ti.stock.domain.model.model.stock.Stock;

public class GetStockUseCase {
    public Stock action(Long id) {
        return Stock.builder().build();
    }
}
