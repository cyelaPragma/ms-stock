package com.acelera.ti.stock.domain.usecase.orchestrator;

import com.acelera.ti.stock.domain.model.model.parameters.FilterParameters;
import com.acelera.ti.stock.domain.model.model.product.Brand;
import com.acelera.ti.stock.domain.model.model.product.Category;
import com.acelera.ti.stock.domain.model.model.stock.Stock;
import com.acelera.ti.stock.domain.usecase.GetAllStockUseCase;
import com.acelera.ti.stock.domain.usecase.GetPageStockUseCase;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class FilterStockByParametersUseCase {

    private final GetAllStockUseCase getAllStockUseCase;
    private final GetPageStockUseCase getPageStockUseCase;

    public List<Stock> action(FilterParameters filterParameter, int pageNumber, int pageSize) {

        List<Stock> stocks = getAllStockUseCase.action();
        FilterList filters = new FilterList();

        if (filterParameter.getBrand() != null && !filterParameter.getBrand().isEmpty()) {
            List<String> brands = filterParameter.getBrand().stream().map(Brand::getName).collect(Collectors.toList());
            filters.addFilter(s -> brands.contains(s.getProduct().getBrand().getName()));
        }

        if (filterParameter.getCategory() != null && !filterParameter.getCategory().isEmpty()) {
            List<String> categories = filterParameter.getCategory().stream().map(Category::getName).collect(Collectors.toList());
            filters.addFilter(s -> categories.contains(s.getProduct().getCategory().getName()));
        }

        if (filterParameter.getMinPrice() != null && filterParameter.getMaxPrice() != null) {
            filters.addFilter(s -> s.getSellPrice() >= filterParameter.getMinPrice()
                    && s.getSellPrice() <= filterParameter.getMaxPrice());
        }

        List<Stock> stockFilter = stocks.stream().filter(filters.getFilter()).collect(Collectors.toList());
        return getPageStockUseCase.action(stockFilter, pageNumber, pageSize);
    }


     static class FilterList {
        List<Predicate<Stock>> filters = new ArrayList<>();

        public void addFilter(Predicate<Stock> filter) {
            filters.add(filter);
        }

        public Predicate<Stock> getFilter() {
            return filters.stream().reduce(Predicate::and).orElse(p -> true);
        }
    }

}
