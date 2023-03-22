package com.acelera.ti.stock.domain.usecase;

import com.acelera.ti.stock.domain.model.exceptions.NotExistProductsException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    void readAllStockSuccess() {
        when(stockRepository.getAllStock()).thenReturn(StockMocks.getStocks(5));
        List<Stock> stockResponse = getAllStockUseCase.getAllStocks();
        assertEquals(5, stockResponse.size());
    }

    @Test
    void readAllProductsEmpty() {
        when(stockRepository.getAllStock()).thenReturn(StockMocks.getStocks(0));
        assertThrows(NotExistProductsException.class, () -> getAllStockUseCase.getAllStocks());
    }

    @Test
    void readAllProductsTechnicalError() {
        doThrow(TechnicalException.class).when(stockRepository).getAllStock();
        assertThrows(TechnicalException.class, () -> getAllStockUseCase.getAllStocks());
    }
}