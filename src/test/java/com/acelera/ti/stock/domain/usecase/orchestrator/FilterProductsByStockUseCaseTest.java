package com.acelera.ti.stock.domain.usecase.orchestrator;

import com.acelera.ti.stock.domain.model.model.stock.FilterParameters;
import com.acelera.ti.stock.domain.model.model.stock.Stock;
import com.acelera.ti.stock.domain.usecase.GetAllStockUseCase;
import com.acelera.ti.stock.mock.product.ProductMocks;
import com.acelera.ti.stock.mock.stock.FilterParametersMocks;
import com.acelera.ti.stock.mock.stock.StockMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    @DisplayName("Filter products with all parameters")
    void testFilterByAllParameters() {
        // Configurar
        FilterParameters filterParameters = FilterParametersMocks.getFilterAllParameters();
        List<Stock> expectedStocks = List.of(
                Stock.builder().id(3L).product(ProductMocks.getProduct(3L)).sellPrice(10003.0).amount(10).build()
        );
        when(getAllStockUseCase.action()).thenReturn(StockMocks.getStockFilters());

        // Ejecutar
        List<Stock> actualFilterResult = filterProductsByStockUseCase.action(filterParameters);

        // Verificar
        assertEquals(expectedStocks, actualFilterResult);
        verify(getAllStockUseCase).action();
    }

    @Test
    @DisplayName("Filter products with invalid filter parameters")
    void testFilterInvalidFilterParameters() {
        // Configurar
        FilterParameters filterParameters = FilterParametersMocks.getInvalidFilterParameters();

        // Ejecutar
        when(getAllStockUseCase.action()).thenReturn(StockMocks.getStockFilters());
        List<Stock> actualFilterResult = filterProductsByStockUseCase.action(filterParameters);

        // Verificar
        assertTrue(actualFilterResult.isEmpty(), "El resultado del filtro debe estar vacío");
    }

    @Test
    @DisplayName("Filter products by price")
    void testActionFilterByPrice() {
        // Configurar
        double minPrice = 10001.0;
        double maxPrice = 10003.0;
        FilterParameters filterParameters = FilterParametersMocks.getFilterByPrice(minPrice, maxPrice);
        List<Stock> expectedStocks = List.of(
                Stock.builder().id(1L).product(ProductMocks.getProduct(1L)).sellPrice(10001.0).amount(10).build(),
                Stock.builder().id(2L).product(ProductMocks.getProduct(2L)).sellPrice(10002.0).amount(10).build(),
                Stock.builder().id(3L).product(ProductMocks.getProduct(3L)).sellPrice(10003.0).amount(10).build()
        );

        // Ejecutar
        when(getAllStockUseCase.action()).thenReturn(StockMocks.getStockFilters());
        List<Stock> actualFilterResult = filterProductsByStockUseCase.action(filterParameters);

        // Verificar
        assertEquals(expectedStocks, actualFilterResult,
                "Los productos filtrados deben coincidir con las existencias esperadas");
    }

    @Test
    @DisplayName("Filter products by Category")
    void testActionFilterByCategory() {
        //configurar
        FilterParameters filterParameters = FilterParametersMocks.getFilterByCategory(4, 5);
        List<Stock> expectedStocks = List.of(
                Stock.builder().id(4L).product(ProductMocks.getProduct(4L)).sellPrice(10004.0).amount(10).build(),
                Stock.builder().id(5L).product(ProductMocks.getProduct(5L)).sellPrice(10005.0).amount(10).build()

        );

        //Ejecutar
        when(getAllStockUseCase.action()).thenReturn(StockMocks.getStockFilters());
        List<Stock> actualFilterResult = filterProductsByStockUseCase.action(filterParameters);

        //Validar
        assertEquals(expectedStocks, actualFilterResult,
                "Los productos filtrados deben coincidir con las existencias esperadas");
    }

    @Test
    @DisplayName("Filter products by Brand")
    void testActionFilterByBrand() {
        //configurar
        FilterParameters filterParameters = FilterParametersMocks.getFilterByBrand(1, 3);
        List<Stock> expectedStocks = List.of(
                Stock.builder().id(1L).product(ProductMocks.getProduct(1L)).sellPrice(10001.0).amount(10).build(),
                Stock.builder().id(2L).product(ProductMocks.getProduct(2L)).sellPrice(10002.0).amount(10).build(),
                Stock.builder().id(3L).product(ProductMocks.getProduct(3L)).sellPrice(10003.0).amount(10).build()
        );

        //Ejecutar
        when(getAllStockUseCase.action()).thenReturn(StockMocks.getStockFilters());
        List<Stock> actualFilterResult = filterProductsByStockUseCase.action(filterParameters);

        //Validar
        assertEquals(expectedStocks, actualFilterResult,
                "Los productos filtrados deben coincidir con las existencias esperadas");
    }

    @Test
    @DisplayName("Filter products by Brand and Category")
    void testActionFilterByBrandAndCategory(){
        //configurar
        FilterParameters filterParameters = FilterParametersMocks.getFilterByBrandAndCategory();
        List<Stock> expectedStocks = List.of(
                Stock.builder().id(4L).product(ProductMocks.getProduct(4L)).sellPrice(10004.0).amount(10).build(),
                Stock.builder().id(5L).product(ProductMocks.getProduct(5L)).sellPrice(10005.0).amount(10).build()
        );

        //Ejecutar
        when(getAllStockUseCase.action()).thenReturn(StockMocks.getStockFilters());
        List<Stock> actualFilterResult = filterProductsByStockUseCase.action(filterParameters);

        //Validar
        assertEquals(expectedStocks, actualFilterResult,
                "Los productos filtrados deben coincidir con las existencias esperadas");
    }

    @Test
    @DisplayName("Filter products by Brand and Price")
    void testActionFilterByBrandAndPrice(){
        // Configurar
        FilterParameters filterParameters = FilterParametersMocks.getFilterByBrandAndPrice(10001.0, 10005.0);
        List<Stock> expectedStocks = List.of(
                Stock.builder().id(1L).product(ProductMocks.getProduct(1L)).sellPrice(10001.0).amount(10).build(),
                Stock.builder().id(2L).product(ProductMocks.getProduct(2L)).sellPrice(10002.0).amount(10).build(),
                Stock.builder().id(3L).product(ProductMocks.getProduct(3L)).sellPrice(10003.0).amount(10).build(),
                Stock.builder().id(4L).product(ProductMocks.getProduct(4L)).sellPrice(10004.0).amount(10).build(),
                Stock.builder().id(5L).product(ProductMocks.getProduct(5L)).sellPrice(10005.0).amount(10).build()
        );
        when(getAllStockUseCase.action()).thenReturn(StockMocks.getStockFilters());
        // Ejecutar

        List<Stock> actualFilterResult = filterProductsByStockUseCase.action(filterParameters);

        // Validar
        assertEquals(expectedStocks, actualFilterResult,
                "Los productos filtrados deben coincidir con las existencias esperadas");
    }

    @Test
    @DisplayName("Filter products by Price and Category")
    void testActionFilterByPriceAndCategory() {
        // Configurar
        FilterParameters filterParameters = FilterParametersMocks.getFilterByPriceAndCategory(10006.0, 10007.0);
        List<Stock> expectedStocks = List.of(
                Stock.builder().id(7L).product(ProductMocks.getProduct(7L)).sellPrice(10007.0).amount(10).build()
        );

        //Ejecutar
        when(getAllStockUseCase.action()).thenReturn(StockMocks.getStockFilters());
        List<Stock> actualFilterResult = filterProductsByStockUseCase.action(filterParameters);

        //Validar
        assertEquals(expectedStocks, actualFilterResult,
                "Los productos filtrados deben coincidir con las existencias esperadas");

    }
}
