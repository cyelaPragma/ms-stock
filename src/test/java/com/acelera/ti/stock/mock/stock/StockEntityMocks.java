package com.acelera.ti.stock.mock.stock;

import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.entity.StockEntity;
import java.util.ArrayList;
import java.util.List;

public class StockEntityMocks {
    public static List<StockEntity> getStockEntities(int size){
        List<StockEntity> stocksEntities = new ArrayList<>();
        for(int i = 1; i <= size; i++){
            stocksEntities.add(getStockEntity(i));
        }
        return stocksEntities;
    }
    private static StockEntity getStockEntity(long id) {
        return StockEntity.builder()
                .id(id)
                .amount(10)
                .sellPrice(2000.0)
                .productId(id)
                .build();
    }
}