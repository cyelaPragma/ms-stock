package com.acelera.ti.stock.domain.usecase;

import com.acelera.ti.stock.domain.model.exceptions.NotExistStockPageException;
import com.acelera.ti.stock.domain.model.model.stock.Stock;
import java.util.List;

public class GetPageStockUseCase {
    public List<Stock> action(List<Stock> stock, int pageNumber, int pageSize) {
        if(pageSize == 0) {
            pageSize = 20;
        }
        if(!isPageNumberExist(pageNumber,pageSize,stock.size())) {
            throw new NotExistStockPageException();
        }
        return stock.stream()
                .skip((long) pageNumber * pageSize)
                .limit(pageSize)
                .toList();
    }

    private static boolean isPageNumberExist(int pageNumber, int pageSize, int stock) {
        int totalPages = stock/pageSize;
        if(stock%pageSize != 0){
            totalPages++;
        }
        return pageNumber <= totalPages;
    }
}
