package com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.getways;

import com.acelera.ti.stock.domain.model.gateways.repositories.StockRepository;
import com.acelera.ti.stock.domain.model.model.product.Product;
import com.acelera.ti.stock.domain.model.model.stock.Stock;
import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.entity.StockEntity;
import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.mapper.StockMapper;
import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.repository.StockJpaRepository;
import com.acelera.ti.stock.infrastructure.drivenadapters.productservice.services.ProductServicesImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class StockRepositoryImpl implements StockRepository {
    private final StockJpaRepository stockJpaRepository;
    private final StockMapper stockMapper;
    private final ProductServicesImpl productServices;

    @Override
    public List<Stock> getAllStock() {
        List<Stock> stocks = stockMapper.entitiesToStocks(stockJpaRepository.findAll());
        List<Stock> stockResponse = new ArrayList<>();

        for (Stock stock : stocks) {
            Product product = productServices.getProductById(stock.getProduct().getId());
            stock.setProduct(product);
            stockResponse.add(stock);
        }

        return stockResponse;
    }

    @Override
    public Stock getStockById(Long id) {
        StockEntity stockEntity = stockJpaRepository.findById(id).orElse(null);
        assert stockEntity != null;
        return stockMapper.stockEntityToStock(stockEntity);
    }

    @Override
    public Stock saveStock(Stock stock) {
        if (stock == null) {
            throw new IllegalArgumentException("El objeto stock no puede ser nulo");
        }
        StockEntity stockEntity = stockMapper.stockToStockEntity(stock);
        return stockMapper.stockEntityToStock(stockJpaRepository.save(stockEntity));
    }
}
