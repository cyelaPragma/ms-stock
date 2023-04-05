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
        List<Stock> inputStocks  = StockMocks.getStocks(10);
        int pageNumber = 1;
        int pageSize = 2;
        List<Stock> expectedStocksPage = inputStocks.subList(2, 4);

        // Ejecutar
        List<Stock> actualStocksPage  = getPageStockUseCase.action(inputStocks , pageNumber, pageSize);

        // Verificar
        assertEquals(pageSize, actualStocksPage .size(), "El tamaño de la pagina debe ser: " + pageSize);
        assertArrayEquals(expectedStocksPage.toArray(), actualStocksPage.toArray(),
                "La página devuelta no coincide con la página esperada");
    }

    @Test
    @Name("verify that the default page size of page is 20")
    void testGetDefaultPageSize() {
        // Configurar
        List<Stock> inputStocks = StockMocks.getStocks(30);
        int pageNumber = 0;
        int pageSize = 0;

        // Ejecutar
        List<Stock> actualStocksPage = getPageStockUseCase.action(inputStocks, pageNumber, pageSize);

        // Verificar
        assertEquals(20, actualStocksPage.size(), "El tamaño de pagina predeterminado debe ser 20");


    }

    @Test
    @Name("check when there is more than one page of results and that the results are as expected on each page")
    void testGetMultiplePageStocks() {
        // Configurar
        List<Stock> inputStocks = StockMocks.getStocks(25);
        int pageSize = 10;

        // Ejecutar
        List<Stock> expectedPage1 = getPageStockUseCase.action(inputStocks, 0, pageSize);
        List<Stock> expectedPage2 = getPageStockUseCase.action(inputStocks, 1, pageSize);
        List<Stock> expectedPage3 = getPageStockUseCase.action(inputStocks, 2, pageSize);

        // Verificar
        assertAll(() -> {
            assertArrayEquals(inputStocks.subList(0, 10).toArray(), expectedPage1.toArray(),
                    "La página 1 no contiene los resultados esperados");
        }, () -> {
            assertArrayEquals(inputStocks.subList(10, 20).toArray(), expectedPage2.toArray(),
                    "La página 2 no contiene los resultados esperados");
        }, () -> {
            assertArrayEquals(inputStocks.subList(20, 25).toArray(), expectedPage3.toArray(),
                    "La página 3 no contiene los resultados esperados");
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
        assertThrows(NotExistStockPageException.class, () -> getPageStockUseCase.action(stocks, pageNumber, pageSize),
                "Debe lanzar una excepción si la lista de stocks es nula");
    }
}