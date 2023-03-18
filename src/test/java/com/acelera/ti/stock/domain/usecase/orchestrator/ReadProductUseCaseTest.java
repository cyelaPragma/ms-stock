package com.acelera.ti.stock.domain.usecase.orchestrator;

import com.acelera.ti.stock.domain.model.gateways.services.ProductServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReadProductUseCaseTest {

    @InjectMocks
    private ReadProductUseCase readProductUseCase;

    @Mock
    private ProductServices productServices;

    @BeforeEach
    void setUp() {
        readProductUseCase = new ReadProductUseCase(productServices);
    }

    @Test
    void readAllProductsSuccess() {

    }

    @Test
    void readAllProductsEmpty() {

    }

    @Test
    void readAllProductsTechnicalError() {

    }

    @Test
    void readProductByIdSuccess() {

    }

    @Test
    void readProductByIdNotFound() {

    }

    @Test
    void readProductByIdTechnicalError() {

    }
}