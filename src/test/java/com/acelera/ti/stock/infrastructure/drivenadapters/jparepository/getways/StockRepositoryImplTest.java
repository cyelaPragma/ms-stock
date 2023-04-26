package com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.getways;

import com.acelera.ti.stock.domain.model.model.stock.Stock;
import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.entity.StockEntity;
import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.mapper.StockMapper;
import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.repository.StockJpaRepository;
import com.acelera.ti.stock.infrastructure.drivenadapters.productservice.services.ProductServicesImpl;
import com.acelera.ti.stock.mock.product.ProductMocks;
import com.acelera.ti.stock.mock.stock.StockEntityMocks;
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
class StockRepositoryImplTest {
    @InjectMocks
    private StockRepositoryImpl stockRepository;
    @Mock
    private StockJpaRepository stockJpaRepository;
    @Mock
    private StockMapper stockMapper;
    @Mock
    private ProductServicesImpl productServices;

    @BeforeEach
    void setUp(){
        stockRepository = new StockRepositoryImpl(stockJpaRepository,stockMapper,productServices);
    }
    @Test
    void findByAmountGreaterThanAndSellPriceIsNotNull() {
        List<StockEntity> stockEntities = StockEntityMocks.getStockEntities(3);
        when(stockJpaRepository.findByAmountGreaterThanAndSellPriceIsNotNull(0)).thenReturn(stockEntities);
        when(stockMapper.entitiesToStocks(stockEntities)).thenReturn(StockMocks.getStocks(3));
        when(productServices.getProductById(1L)).thenReturn(ProductMocks.getProduct(1L));
        when(productServices.getProductById(2L)).thenReturn(ProductMocks.getProduct(2L));
        when(productServices.getProductById(3L)).thenReturn(ProductMocks.getProduct(3L));
        List<Stock> stockResponse = stockRepository.findByAmountGreaterThanAndSellPriceIsNotNull();
        assertTrue(stockResponse.size()>0);
        assertArrayEquals(StockMocks.getStocks(3).toArray(), stockResponse.toArray());
    }
    @Test
    void findProductsForSaleWhenNotExistStocks() {
        List<StockEntity> stockEntities = StockEntityMocks.getStockEntities(3);
        when(stockJpaRepository.findByAmountGreaterThanAndSellPriceIsNotNull(0)).thenReturn(null);
        when(stockMapper.entitiesToStocks(stockEntities)).thenReturn(null);
        List<Stock> stockResponse = stockRepository.findByAmountGreaterThanAndSellPriceIsNotNull();
        assertTrue(stockResponse.isEmpty());
    }
}