package com.acelera.ti.stock.domain.usecase;

import com.acelera.ti.stock.domain.model.exceptions.ProductNotFoundException;
import com.acelera.ti.stock.domain.model.exceptions.TechnicalException;
import com.acelera.ti.stock.domain.model.gateways.services.ProductServices;
import com.acelera.ti.stock.domain.model.model.product.Product;
import com.acelera.ti.stock.domain.usecase.GetProductUseCase;
import com.acelera.ti.stock.mock.product.ProductMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class GetProductUseCaseTest {
    @InjectMocks
    private GetProductUseCase readProductUseCase;
    @Mock
    private ProductServices productServices;
    @BeforeEach
    void setUp() {
        readProductUseCase = new GetProductUseCase(productServices);
    }
    @Test
    void readProductByIdSuccess() {
        when(productServices.getProductById(1L)).thenReturn(ProductMocks.getProduct(1L));
        Product productResponse = readProductUseCase.action(1L);
        assertEquals(ProductMocks.getProduct(1L), productResponse);
    }
    @Test
    void readProductByIdNotFound() {
        when(productServices.getProductById(1L)).thenReturn(null);
        assertThrows(ProductNotFoundException.class, () -> readProductUseCase.action(1L));
    }
    @Test
    void readProductByIdTechnicalError() {
        doThrow(TechnicalException.class).when(productServices).getProductById(1L);
        assertThrows(TechnicalException.class, () -> readProductUseCase.action(1L));
    }
}
