package com.acelera.ti.stock.domain.usecase;

import com.acelera.ti.stock.domain.model.exceptions.NotExistProductsException;
import com.acelera.ti.stock.domain.model.exceptions.TechnicalException;
import com.acelera.ti.stock.domain.model.gateways.services.ProductServices;
import com.acelera.ti.stock.domain.model.model.product.Product;
import com.acelera.ti.stock.mock.product.ProductMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
class GetAllProductsUseCaseTest {
    @InjectMocks
    private GetAllProductsUseCase getAllProductsUseCase;
    @Mock
    private ProductServices productServices;

    @BeforeEach
    void setUp() {
        getAllProductsUseCase = new GetAllProductsUseCase(productServices);
    }

    @Test
    void readAllProductsSuccess() {
        when(productServices.getAllProducts()).thenReturn(ProductMocks.getProducts(5));
        List<Product> productsResponse = getAllProductsUseCase.action();
        assertTrue(productsResponse.size() > 0);
        assertArrayEquals(ProductMocks.getProducts(5).toArray(), productsResponse.toArray());
    }

    @Test
    void readAllProductsEmpty() {
        when(productServices.getAllProducts()).thenReturn(ProductMocks.getProducts(0));
        assertThrows(NotExistProductsException.class, () -> getAllProductsUseCase.action());
    }

    @Test
    void readAllProductsNull() {
        when(productServices.getAllProducts()).thenReturn(null);
        assertThrows(NotExistProductsException.class, () -> getAllProductsUseCase.action());
    }

    @Test
    void readAllProductsTechnicalError() {
        doThrow(TechnicalException.class).when(productServices).getAllProducts();
        assertThrows(TechnicalException.class, () -> getAllProductsUseCase.action());
    }
}