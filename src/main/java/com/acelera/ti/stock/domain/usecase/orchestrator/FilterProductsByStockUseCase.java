package com.acelera.ti.stock.domain.usecase.orchestrator;

import com.acelera.ti.stock.domain.model.exceptions.StockNotFoundException;
import com.acelera.ti.stock.domain.model.exceptions.TechnicalException;
import com.acelera.ti.stock.domain.model.model.product.Brand;
import com.acelera.ti.stock.domain.model.model.product.Category;
import com.acelera.ti.stock.domain.model.model.stock.FilterParameters;
import com.acelera.ti.stock.domain.model.model.stock.Stock;
import com.acelera.ti.stock.domain.usecase.GetAllStockUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class FilterProductsByStockUseCase {

    private final GetAllStockUseCase getAllStockUseCase;

    public List<Stock> action(FilterParameters filterParameter) {
        try {
            List<Stock> stocks = getAllStockUseCase.action();
            ListParametersFilter filters = new ListParametersFilter();

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

            return stocks.stream().filter(filters.getFilter()).collect(Collectors.toList());
        }catch (StockNotFoundException | TechnicalException e) {
            log.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    private static class ListParametersFilter {
        List<Predicate<Stock>> filters = new ArrayList<>();
        public void addFilter(Predicate<Stock> filter) {
            filters.add(filter);
        }
        public Predicate<Stock> getFilter() {
            return filters.stream().reduce(Predicate::and).orElse(p -> true);
        }
    }
}
