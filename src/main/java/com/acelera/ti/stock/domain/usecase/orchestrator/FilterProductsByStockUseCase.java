package com.acelera.ti.stock.domain.usecase.orchestrator;

import com.acelera.ti.stock.domain.model.exceptions.StockNotFoundException;
import com.acelera.ti.stock.domain.model.model.product.Brand;
import com.acelera.ti.stock.domain.model.model.product.Category;
import com.acelera.ti.stock.domain.model.model.stock.FilterParameters;
import com.acelera.ti.stock.domain.model.model.stock.Stock;
import com.acelera.ti.stock.domain.usecase.GetAllStockUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FilterProductsByStockUseCase {

    private final GetAllStockUseCase getAllStockUseCase;

    public List<Stock> action(FilterParameters filterParameter){
        List<Stock> stock = getAllStockUseCase.getAllStocks();

       stock = filterProductByPrice(filterParameter.getMinPrice(), filterParameter.getMaxPrice(), stock);
       stock = filterProductByBrand(filterParameter.getBrand(), stock);
       stock = filterProductByCategory(filterParameter.getCategory(), stock);

       if (stock.isEmpty()){
           throw  new StockNotFoundException();
       }

        return stock;
    }

    private List<Stock> filterProductByPrice(Double minPrice, Double maxPrice, List<Stock> stock) {
        return stock.stream()
                .filter(s -> s.getSellprice().equals(maxPrice)).toList();
    }

    private List<Stock> filterProductByBrand(List<Brand> brands, List<Stock> stock) {
        return stock.stream()
                .filter(s -> s.getProduct().getBrand().getName().equals(brands)).toList();
    }

    private List<Stock> filterProductByCategory(List<Category> categories, List<Stock> stock) {
        return stock.stream()
                .filter(s -> s.getProduct().getCategory().getName().equals(categories)).toList();
    }
}
