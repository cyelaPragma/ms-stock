package com.acelera.ti.stock.domain.usecase;

import com.acelera.ti.stock.domain.model.exceptions.NotExistStockPageException;
import com.acelera.ti.stock.domain.model.model.stock.Stock;
import com.acelera.ti.stock.mock.stock.StockMocks;
import jdk.jfr.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GetPageStockUseCaseTest {

    private GetPageStockUseCase getPageStockUseCase;

    @BeforeEach
    void setUp() {
        getPageStockUseCase = new GetPageStockUseCase();
    }

    @Test
    @Name("Test to check if the correct page is returned")
    void testGetPageStock() {
        // Configurar
        List<Stock> stocks = StockMocks.getStocks(10);
        int pageNumber = 1;
        int pageSize = 2;

        // Ejecutar
        List<Stock> actualStocks = getPageStockUseCase.action(stocks, pageNumber, pageSize);

        // Verificar
        assertEquals(pageSize, actualStocks.size(), "El tamaño de la pagina debe ser: " + pageSize);
    }

    @Test
    @Name("verify that the default page size of page is 20")
    void testGetDefaultPageSize() {
        // Configurar
        List<Stock> stocks = StockMocks.getStocks(30);
        int pageNumber = 0;
        int pageSize = 0;

        // Ejecutar
        List<Stock> expectedStocks = getPageStockUseCase.action(stocks, pageNumber, pageSize);

        // Verificar
        assertEquals(20, expectedStocks.size(), "El tamaño de pagina predeterminado debe ser 20");
    }

    @Test
    @Name("check when there is more than one page of results and that the results are as expected on each page")
    void testGetMultiplePageStocks() {
        // Configurar
        List<Stock> stocks = StockMocks.getStocks(25);
        int pageSize = 10;

        // Ejecutar
        List<Stock> expectedPage1 = getPageStockUseCase.action(stocks, 1, pageSize);
        List<Stock> expectedPage2 = getPageStockUseCase.action(stocks, 2, pageSize);
        List<Stock> expectedPage3 = getPageStockUseCase.action(stocks, 3, pageSize);

        // Verificar
        assertAll(() ->{
            assertEquals(10, expectedPage1.size(), "El número de resultados en la página 1 es incorrecto");
        }, () ->{
            assertEquals(10, expectedPage2.size(), "El número de resultados en la página 2 es incorrecto");
        }, () ->{
            assertEquals(5, expectedPage3.size(), "El número de resultados en la página 3 es incorrecto");
        });
    }

    @Test
    @Name("check if NotExistStockPageException is thrown when requesting a non-existent page")
    void testGetNotExistPageStock() {
        // Configurar
        List<Stock> stocks = StockMocks.getStocks(10);
        int pageNumber = 7;
        int pageSize = 8;

        // Ejecutar y verificar
        assertThrows(NotExistStockPageException.class, () -> getPageStockUseCase.action(stocks, pageNumber, pageSize),
                "Debe lanzar una excepción si la página de stock no existe");
    }

    @Test
    @Name("Test para verificar que la función maneja correctamente la entrada nula")
    void testGetPageStockWithNullInput() {
        // Configurar
        List<Stock> stocks = null;
        int pageNumber = 1;
        int pageSize = 10;

        // Ejecutar y verificar
        assertThrows(IllegalArgumentException.class, () -> getPageStockUseCase.action(stocks, pageNumber, pageSize),
                "Debe lanzar una excepción si la lista de stocks es nula");
    }

}