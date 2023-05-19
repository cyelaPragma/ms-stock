package com.acelera.ti.stock.infrastructure.entrypoints.rest.mapper;

import com.acelera.ti.stock.domain.model.model.stock.Stock;
import com.acelera.ti.stock.infrastructure.entrypoints.rest.dto.ProductForSaleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductForSaleMapper {
    List<ProductForSaleDto> stocksToProductsForSaleDto(List<Stock> stocks);

    @Mapping(target = "description", source = "stock.product.description")
    @Mapping(target = "name", source = "stock.product.name")
    ProductForSaleDto stockToProductForSaleDto(Stock stock);
}
