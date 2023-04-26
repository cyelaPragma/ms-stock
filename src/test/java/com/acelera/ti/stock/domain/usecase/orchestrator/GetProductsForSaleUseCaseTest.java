package com.acelera.ti.stock.domain.usecase.orchestrator;

import com.acelera.ti.stock.domain.model.model.parameters.FilterProductsForSaleParameters;
import com.acelera.ti.stock.domain.model.model.stock.Stock;
import com.acelera.ti.stock.domain.usecase.GetPageStockUseCase;
import com.acelera.ti.stock.domain.usecase.GetStocksForSaleUseCase;
import com.acelera.ti.stock.mock.stock.FilterProductsForSaleParametersMocks;
import com.acelera.ti.stock.mock.stock.StockMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class GetProductsForSaleUseCaseTest {
    @InjectMocks
    private GetProductsForSaleUseCase getProductsForSaleUseCase;
    @Mock
    private GetPageStockUseCase getPageStockUseCase;
    @Mock
    private GetStocksForSaleUseCase getStocksForSaleUseCase;

    @BeforeEach
    void setUp() {
        getProductsForSaleUseCase = new GetProductsForSaleUseCase(getStocksForSaleUseCase, getPageStockUseCase);
    }
    @Test
    void getProductsForSaleWithAllParameters() {
        List<Stock> stockList = StockMocks.getStocks(15);
        when(getStocksForSaleUseCase.action()).thenReturn(stockList);
        when(getPageStockUseCase.action(stockList.subList(0,3), 0,5)).thenReturn(stockList.subList(0,3));
        FilterProductsForSaleParameters filterProductsForSaleParameters = FilterProductsForSaleParametersMocks.getFilterAllParameters();
        List<Stock> stocks = getProductsForSaleUseCase.action(filterProductsForSaleParameters,0,5);
        assertArrayEquals(stockList.subList(0,3).toArray(), stocks.toArray());
    }
    @Test
    void getProductsForSaleByProductName() {
        List<Stock> stockList = StockMocks.getStocks(10);
        when(getStocksForSaleUseCase.action()).thenReturn(stockList);
        when(getPageStockUseCase.action(stockList.subList(3,7), 0,5)).thenReturn(stockList.subList(3,7));
        FilterProductsForSaleParameters filterProductsForSaleParameters = FilterProductsForSaleParametersMocks.getFilterByProductName();
        List<Stock> stocks = getProductsForSaleUseCase.action(filterProductsForSaleParameters, 0,5);
        assertArrayEquals(stockList.subList(3,7).toArray(), stocks.toArray());
    }
    @Test
    void getProductsForSaleByCategoryName() {
        List<Stock> stockList = StockMocks.getStocks(10);
        when(getStocksForSaleUseCase.action()).thenReturn(stockList);
        when(getPageStockUseCase.action(stockList.subList(2,8), 0,5)).thenReturn(stockList.subList(2,8));
        FilterProductsForSaleParameters filterProductsForSaleParameters = FilterProductsForSaleParametersMocks.getFilterByCategoryName();
        List<Stock> stocks = getProductsForSaleUseCase.action(filterProductsForSaleParameters, 0, 5);
        assertArrayEquals(stockList.subList(2,8).toArray(), stocks.toArray());
    }
    @Test
    void getProductsForSaleByBrandName() {
        List<Stock> stockList = StockMocks.getStocks(10);
        when(getStocksForSaleUseCase.action()).thenReturn(stockList);
        when(getPageStockUseCase.action(stockList.subList(3,10), 0,5)).thenReturn(stockList.subList(3,9));
        FilterProductsForSaleParameters filterProductsForSaleParameters = FilterProductsForSaleParametersMocks.getFilterByBrandName();
        List<Stock> stocks = getProductsForSaleUseCase.action(filterProductsForSaleParameters, 0,5);
        assertArrayEquals(stockList.subList(3,9).toArray(), stocks.toArray());
    }
    @Test
    void getProductsForSaleByBrandAndProductNames() {
        List<Stock> stockList = StockMocks.getStocks(10);
        when(getStocksForSaleUseCase.action()).thenReturn(stockList);
        when(getPageStockUseCase.action(stockList.subList(3,10), 2,3)).thenReturn(stockList.subList(9,10));
        FilterProductsForSaleParameters filterProductsForSaleParameters = FilterProductsForSaleParametersMocks.getFilterByBrandAndProductName();
        List<Stock> stocks = getProductsForSaleUseCase.action(filterProductsForSaleParameters,2,3);
        assertArrayEquals(stockList.subList(9,10).toArray(), stocks.toArray());
    }
    @Test
    void getProductsForSaleByBrandAndCategoryNames() {
        List<Stock> stockList = StockMocks.getStocks(10);
        when(getStocksForSaleUseCase.action()).thenReturn(stockList);
        when(getPageStockUseCase.action(stockList.subList(2,7), 1,2)).thenReturn(stockList.subList(4,6));
        FilterProductsForSaleParameters filterProductsForSaleParameters = FilterProductsForSaleParametersMocks.getFilterByBrandAndCategoryName();
        List<Stock> stocks = getProductsForSaleUseCase.action(filterProductsForSaleParameters,1,2);
        assertArrayEquals(stockList.subList(4,6).toArray(), stocks.toArray());
    }
    @Test
    void getProductsForSaleByProductAndCategoryNames() {
        List<Stock> stockList = StockMocks.getStocks(10);
        when(getStocksForSaleUseCase.action()).thenReturn(stockList);
        when(getPageStockUseCase.action(stockList.subList(0,7), 0,3)).thenReturn(stockList.subList(0,3));
        FilterProductsForSaleParameters filterProductsForSaleParameters = FilterProductsForSaleParametersMocks.getFilterByProductAndCategoryName();
        List<Stock> stocks = getProductsForSaleUseCase.action(filterProductsForSaleParameters,0,3);
        assertArrayEquals(stockList.subList(0,3).toArray(), stocks.toArray());
    }
    @Test
    void getProductsForSaleWithoutParameters() {
        List<Stock> stockList = StockMocks.getStocks(22);
        when(getStocksForSaleUseCase.action()).thenReturn(stockList);
        when(getPageStockUseCase.action(stockList, 0,0)).thenReturn(stockList.subList(0,21));
        FilterProductsForSaleParameters filterProductsForSaleParameters = FilterProductsForSaleParameters.builder().build();
        List<Stock> stocks = getProductsForSaleUseCase.action(filterProductsForSaleParameters,0,0);
        assertArrayEquals(stockList.subList(0,21).toArray(), stocks.toArray());
    }
    @Test
    void getProductsForSaleWithInvalidParameters() {
        List<Stock> stockList = StockMocks.getStocks(15);
        when(getStocksForSaleUseCase.action()).thenReturn(stockList);
        FilterProductsForSaleParameters filterProductsForSaleParameters = FilterProductsForSaleParametersMocks.getInvalidFilterParameters();
        List<Stock> stocks = getProductsForSaleUseCase.action(filterProductsForSaleParameters,0,5);
        assertTrue(stocks.isEmpty());
    }
}