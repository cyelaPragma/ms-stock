package com.acelera.ti.stock.domain.usecase.orchestrator;

import com.acelera.ti.stock.domain.model.model.stock.FilterParameters;
import com.acelera.ti.stock.domain.model.model.stock.Stock;
import com.acelera.ti.stock.domain.usecase.GetAllStockUseCase;
import com.acelera.ti.stock.mock.product.ProductMocks;
import com.acelera.ti.stock.mock.stock.FilterParametersMocks;
import com.acelera.ti.stock.mock.stock.StockMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
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
        FilterParameters filters = FilterParametersMocks.getFilterParameters();

        Stock stockResponse = Stock.builder()
                .id(3L)
                .amount(10)
                .sellPrice(1200.00)
                .product(ProductMocks.getProduct(3L))
                .build();
        List<Stock> resultFilter = new ArrayList<>();
        resultFilter.add(stockResponse);

        when(getAllStockUseCase.action()).thenReturn(StockMocks.getStockFilters());
        List<Stock> filter = filterProductsByStockUseCase.action(filters);

        assertArrayEquals(resultFilter.toArray(), filter.toArray());

    }

    @Test
    void filterProductByPrice() {
        FilterParameters filters = FilterParametersMocks.getFilterByPrice(10001.0, 10003.0);
        Stock stockResponse = Stock.builder()
                .id(3L)
                .product(ProductMocks.getProduct(3L))
                .sellPrice(10003.0)
                .amount(10)
                .build();
        List<Stock> resultFilter = new ArrayList<>();

        when(filterProductsByStockUseCase.action(filters)).thenReturn(resultFilter);
        List<Stock> filter = filterProductsByStockUseCase.action(filters);
        assertArrayEquals(resultFilter.toArray(), filter.toArray());
    }
}
