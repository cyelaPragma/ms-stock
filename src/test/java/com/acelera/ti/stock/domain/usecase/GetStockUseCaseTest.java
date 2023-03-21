package com.acelera.ti.stock.domain.usecase;

import com.acelera.ti.stock.domain.model.exceptions.StockEmptyException;
import com.acelera.ti.stock.domain.model.exceptions.StockNotFoundException;
import com.acelera.ti.stock.domain.model.exceptions.TechnicalException;
import com.acelera.ti.stock.domain.model.gateways.repositories.StockRepository;
import com.acelera.ti.stock.domain.model.model.stock.Stock;
import com.acelera.ti.stock.mock.stock.StockMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
class GetStockUseCaseTest {
    @InjectMocks
    private GetStockUseCase getStockUseCase;
    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    void setUp() {
        getStockUseCase = new GetStockUseCase(stockRepository);
    }
    @Test
    void readStockByIdSuccess() {
        when(stockRepository.getStockById(1L)).thenReturn(StockMocks.getStock(1L));
        Stock stockResponse = getStockUseCase.action(1L);
        assertEquals(StockMocks.getStock(1L), stockResponse);
    }
    @Test
    void readStockByIdNotFound() {
        when(stockRepository.getStockById(1L)).thenReturn(null);
        assertThrows(StockNotFoundException.class, () -> getStockUseCase.action(1L));
    }
    @Test
    void readStockByIdEmpty() {
        when(stockRepository.getStockById(1L)).thenReturn(StockMocks.getStockEmpty(1L));
        assertThrows(StockEmptyException.class, () -> getStockUseCase.action(1L));
    }
    @Test
    void readStockByIdTechnicalError() {
        doThrow(TechnicalException.class).when(stockRepository).getStockById(1L);
        assertThrows(TechnicalException.class, () -> getStockUseCase.action(1L));
    }
}