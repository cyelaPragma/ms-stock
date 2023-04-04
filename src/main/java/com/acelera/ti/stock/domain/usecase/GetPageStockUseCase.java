package com.acelera.ti.stock.domain.usecase;

import com.acelera.ti.stock.domain.model.exceptions.NotExistStockPageException;
import com.acelera.ti.stock.domain.model.model.stock.Stock;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class GetPageStockUseCase {
    public List<Stock> action(Collection<Stock> stock, int pageNumber, int pageSize) {
        if (pageSize == 0) {
            pageSize = 20;
        }

        if (stock == null || !isPageNumberExist(pageNumber, pageSize, stock.size())) {
            throw new NotExistStockPageException();
        }
        return stock.stream()
                .skip((long) pageNumber * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    private static boolean isPageNumberExist(int pageNumber, int pageSize, int totalStocks) {
        int totalPages = totalStocks / pageSize;
        if (totalStocks % pageSize != 0) {
            totalPages++;
        }
        return pageNumber <= totalPages;
    }
}
