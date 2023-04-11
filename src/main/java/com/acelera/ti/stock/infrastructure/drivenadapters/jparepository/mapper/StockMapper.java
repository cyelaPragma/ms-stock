package com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.mapper;

import com.acelera.ti.stock.domain.model.model.stock.Stock;
import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.entity.StockEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface StockMapper {
    @Mapping(source = "productId", target = "product.id")
    Stock stockEntityToStock(StockEntity stockEntity);

    @Mapping(source = "product.id", target = "productId")
    StockEntity stockToStockEntity(Stock stock);

    List<Stock> entitiesToStocks(List<StockEntity> stockEntities);
}
