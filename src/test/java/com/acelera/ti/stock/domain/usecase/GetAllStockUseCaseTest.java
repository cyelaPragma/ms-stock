package com.acelera.ti.stock.domain.usecase;

import com.acelera.ti.stock.domain.model.exceptions.NotExistStocksException;
import com.acelera.ti.stock.domain.model.exceptions.TechnicalException;
import com.acelera.ti.stock.domain.model.gateways.repositories.StockRepository;
import com.acelera.ti.stock.domain.model.model.stock.Stock;
import com.acelera.ti.stock.mock.stock.StockMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
class GetAllStockUseCaseTest {
    @InjectMocks
    private GetAllStockUseCase getAllStockUseCase;
    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    void setUp() {
        getAllStockUseCase = new GetAllStockUseCase(stockRepository);
    }

    @Test
    void readAllProductsSuccess() {
        when(stockRepository.getAllStock()).thenReturn(StockMocks.getStocks(5));
        List<Stock> stocksResponse = getAllStockUseCase.action();
        assertTrue(stocksResponse.size() > 0);
        assertArrayEquals(StockMocks.getStocks(5).toArray(), stocksResponse.toArray());
    }

    @Test
    void readAllProductsEmpty() {
        when(stockRepository.getAllStock()).thenReturn(StockMocks.getStocks(0));
        assertThrows(NotExistStocksException.class, () -> getAllStockUseCase.action());
    }

    @Test
    void readAllProductsNull() {
        when(stockRepository.getAllStock()).thenReturn(null);
        assertThrows(NotExistStocksException.class, () -> getAllStockUseCase.action());
    }

    @Test
    void readAllProductsTechnicalError() {
        doThrow(TechnicalException.class).when(stockRepository).getAllStock();
        assertThrows(TechnicalException.class, () -> getAllStockUseCase.action());
    }
}