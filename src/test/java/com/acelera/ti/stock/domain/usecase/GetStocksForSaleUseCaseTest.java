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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
class GetStocksForSaleUseCaseTest {
    @InjectMocks
    private GetStocksForSaleUseCase getStocksForSaleUseCase;
    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    void setUp() {
        getStocksForSaleUseCase = new GetStocksForSaleUseCase(stockRepository);
    }

    @Test
    void readStocksForSaleSuccess() {
        when(stockRepository.findStocksWithAmountAndSellPrice()).thenReturn(StockMocks.getStocks(5));
        List<Stock> stockResponse = getStocksForSaleUseCase.action();
        assertTrue(stockResponse.size()>0);
        assertArrayEquals(StockMocks.getStocks(5).toArray(), stockResponse.toArray());
    }

    @Test
    void readStockForSaleEmpty(){
        when(stockRepository.findStocksWithAmountAndSellPrice()).thenReturn(StockMocks.getStocks(0));
        assertThrows(NotExistStocksException.class, () -> getStocksForSaleUseCase.action());
    }

    @Test
    void readStockForSaleNull(){
        when(stockRepository.findStocksWithAmountAndSellPrice()).thenReturn(null);
        assertThrows(NotExistStocksException.class, () -> getStocksForSaleUseCase.action());
    }

    @Test
    void readStockForSaleTechnicalError() {
        doThrow(TechnicalException.class).when(stockRepository).findStocksWithAmountAndSellPrice();
        assertThrows(TechnicalException.class, () -> getStocksForSaleUseCase.action());
    }
}
