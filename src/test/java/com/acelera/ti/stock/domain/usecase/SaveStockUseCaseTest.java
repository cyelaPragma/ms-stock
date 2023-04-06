package com.acelera.ti.stock.domain.usecase;

import com.acelera.ti.stock.domain.model.exceptions.TechnicalException;
import com.acelera.ti.stock.domain.model.gateways.repositories.StockRepository;
import com.acelera.ti.stock.mock.stock.StockMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
class SaveStockUseCaseTest {

    @InjectMocks
    private SaveStockUseCase saveStockUseCase;
    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    void setUp() {
        saveStockUseCase = new SaveStockUseCase(stockRepository);
    }

    @Test
    void saveSuccess() {
        when(stockRepository.getStockById(1L)).thenReturn(StockMocks.getStock(1L));
        assertDoesNotThrow(() -> saveStockUseCase.action(StockMocks.getStock(1L)));
    }

    @Test
    void saveTechnicalError() {
        doThrow(TechnicalException.class).when(stockRepository).saveStock(StockMocks.getStock(1L));
        assertThrows(TechnicalException.class, () -> saveStockUseCase.action(StockMocks.getStock(1L)));
    }
}