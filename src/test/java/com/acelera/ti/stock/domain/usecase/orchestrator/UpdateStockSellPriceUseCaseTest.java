package com.acelera.ti.stock.domain.usecase.orchestrator;
import com.acelera.ti.stock.domain.model.model.stock.Stock;
import com.acelera.ti.stock.domain.usecase.GetStockUseCase;
import com.acelera.ti.stock.domain.usecase.SaveStockUseCase;
import com.acelera.ti.stock.mock.stock.StockMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UpdateStockSellPriceUseCaseTest {
    @InjectMocks
    private UpdateStockSellPriceUseCase updateStockSellPriceUseCase;
    @Mock
    private SaveStockUseCase saveStockUseCase ;
    @Mock
    private GetStockUseCase getStockUseCase;
    @BeforeEach
    void setUp() { updateStockSellPriceUseCase = new UpdateStockSellPriceUseCase(saveStockUseCase, getStockUseCase); }

    @Test
    void updateSuccess() {
        Stock stockToSave = StockMocks.getStock(1L);
        stockToSave.setSellPrice(2500.00);
        when(getStockUseCase.action(1L)).thenReturn(StockMocks.getStock(1L));
        when(saveStockUseCase.action(stockToSave)).thenReturn(stockToSave);
        Stock stockResponse = updateStockSellPriceUseCase.action(StockMocks.getStock(1L).getId(),2500.00);
        assertEquals(2500.00, stockResponse.getSellPrice());
    }
}