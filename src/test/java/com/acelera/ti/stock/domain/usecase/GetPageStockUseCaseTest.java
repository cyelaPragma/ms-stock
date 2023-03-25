package com.acelera.ti.stock.domain.usecase;

import com.acelera.ti.stock.domain.model.exceptions.NotExistStockPageException;
import com.acelera.ti.stock.domain.model.model.stock.Stock;
import com.acelera.ti.stock.mock.stock.StockMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GetPageStockUseCaseTest {
    private GetPageStockUseCase getPageStockUseCase;

    @BeforeEach
    void setUp() {
        getPageStockUseCase = new GetPageStockUseCase();
    }

    @Test
    void getPageStockSuccess() {
        List<Stock> stocks = StockMocks.getStocks(10);
        List<Stock> stocksResponse = getPageStockUseCase.action(stocks,1,5);
        assertArrayEquals(stocks.subList(5,10).toArray(), stocksResponse.toArray());
    }

    @Test
    void getPageStockSuccessWhenPageSizeIsZero() {
        List<Stock> stocks = StockMocks.getStocks(30);
        List<Stock> stocksResponse = getPageStockUseCase.action(stocks,1,0);
        assertArrayEquals(stocks.subList(20,30).toArray(), stocksResponse.toArray());
    }

    @Test
    void getPageStockSuccessWhenPageNumberNotExist() {
        List<Stock> stocks = StockMocks.getStocks(30);
        assertThrows(NotExistStockPageException.class, () -> getPageStockUseCase.action(stocks,7,8));
    }
}