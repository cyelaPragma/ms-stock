package com.acelera.ti.stock.domain.usecase.orchestrator;

import com.acelera.ti.stock.domain.model.exceptions.StockNotFoundException;
import com.acelera.ti.stock.domain.model.model.stock.Stock;
import com.acelera.ti.stock.domain.usecase.GetAllStockUseCase;
import com.acelera.ti.stock.mock.stock.StockMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class FilterProductsByStockUseCaseTest {
    @InjectMocks
    private FilterProductsByStockUseCase filterProductsByStockUseCase;

    @Mock
    private GetAllStockUseCase getAllStockUseCase;

    @BeforeEach
    void setUp() {
        filterProductsByStockUseCase = new FilterProductsByStockUseCase(getAllStockUseCase);
    }

    @Test
    void filterProductSuccess() {
        when(getAllStockUseCase.getAllStocks()).thenReturn(StockMocks.getStocks(3));
        List<Stock> stocksResponse = getAllStockUseCase.getAllStocks();

        for (Stock s : stocksResponse) {
            assertAll(() -> {
                filterProductsByStockUseCase.filterProductByPrice(10000.0);
                assertEquals(10000.0, s.getSellprice());
            }, () -> {
                filterProductsByStockUseCase.filterProductByBrand("marca");
                assertEquals("marca", s.getProduct().getBrand().getName());
            }, () -> {
                filterProductsByStockUseCase.filterProductByCategory("categoria");
                assertEquals("categoria", s.getProduct().getCategory().getName());
            });
        }
/*
        List<Stock> stockPrice = filterProductsByStockUseCase.filterProductByPrice(10000.0);
        List<Stock> stocksBrands = filterProductsByStockUseCase.filterProductByBrand("marca");
        List<Stock> stocksCategories = filterProductsByStockUseCase.filterProductByCategory("categoria");

        stockPrice.forEach((s) -> { assertEquals(10000.0, s.getSellprice());});
        stocksBrands.forEach((s) -> { assertEquals("marcas", s.getProduct().getBrand().getName());});
        stocksCategories.forEach((s) -> { assertEquals("categoria", s.getProduct().getCategory().getName());});

 */
    }

    @Test
    void filterProductEmpty() {
        when(getAllStockUseCase.getAllStocks()).thenReturn(StockMocks.getStocks(0));
        assertAll(() -> {
            assertThrows(StockNotFoundException.class,
                    () -> filterProductsByStockUseCase.filterProductByPrice(10000.0));

        }, () -> {
            assertThrows(StockNotFoundException.class,
                    () -> filterProductsByStockUseCase.filterProductByBrand("marca"));
        }, () -> {
            assertThrows(StockNotFoundException.class,
                    () -> filterProductsByStockUseCase.filterProductByCategory("categoria"));
        });
    }
}
