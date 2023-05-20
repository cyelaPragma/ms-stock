package com.acelera.ti.stock.domain.usecase;

import com.acelera.ti.stock.domain.model.exceptions.NotExistStockPageException;
import com.acelera.ti.stock.domain.model.model.stock.Stock;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class GetPageStockUseCase {
    static final int PAGE_SIZE_DEFAULT = 20;
    private static boolean isPageNumberExist(int pageNumber, int pageSize, int totalStocks) {
        int totalPages = (int) Math.ceil((double) totalStocks / pageSize);
        return pageNumber <= totalPages;
    }

    public List<Stock> action(Collection<Stock> stock, int pageNumber, int pageSize) {
        if (pageSize == 0) {
            pageSize = PAGE_SIZE_DEFAULT;
        }

        if (stock == null || !isPageNumberExist(pageNumber, pageSize, stock.size())) {
            throw new NotExistStockPageException();
        }
        return stock.stream()
                .skip((long) pageNumber * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }
}
