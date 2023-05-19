package com.acelera.ti.stock.domain.usecase.orchestrator;

import com.acelera.ti.stock.domain.model.model.parameters.FilterProductsForSaleParameters;
import com.acelera.ti.stock.domain.model.model.stock.Stock;
import com.acelera.ti.stock.domain.usecase.GetPageStockUseCase;
import com.acelera.ti.stock.domain.usecase.GetStocksForSaleUseCase;
import lombok.RequiredArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetProductsForSaleUseCase {
    private final GetStocksForSaleUseCase getStocksForSaleUseCase;
    private final GetPageStockUseCase getPageStockUseCase;

    public List<Stock> action(FilterProductsForSaleParameters filterProductsForSaleParameters,int pageNumber, int pageSize){
        List<Stock> stocks = getStocksForSaleUseCase.action();
        List<Predicate<Stock>> filters = getFilters(filterProductsForSaleParameters);
        List<Stock> stocksResponse = stocks.stream().filter(filters.stream().reduce(Predicate::and).orElse(p -> true)).collect(Collectors.toList());
        return getPageStockUseCase.action(stocksResponse, pageNumber, pageSize);
    }

    private List<Predicate<Stock>> getFilters(FilterProductsForSaleParameters filterProductsForSaleParameters) {
        List<Predicate<Stock>> filters = new ArrayList<>();
        if(filterProductsForSaleParameters.getProductsName() != null && !filterProductsForSaleParameters.getProductsName().isEmpty()) {
            filters.add(s -> filterProductsForSaleParameters.getProductsName().contains(s.getProduct().getName()));
        }
        if(filterProductsForSaleParameters.getBrandsName() != null && !filterProductsForSaleParameters.getBrandsName().isEmpty()) {
            filters.add(s -> filterProductsForSaleParameters.getBrandsName().contains(s.getProduct().getBrand().getName()));
        }
        if(filterProductsForSaleParameters.getCategoriesName() != null && !filterProductsForSaleParameters.getCategoriesName().isEmpty()) {
            filters.add(s -> filterProductsForSaleParameters.getCategoriesName().contains(s.getProduct().getCategory().getName()));
        }
        return filters;
    }
}
