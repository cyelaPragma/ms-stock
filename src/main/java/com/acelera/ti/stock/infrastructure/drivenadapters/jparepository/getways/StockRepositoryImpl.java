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
import java.util.Optional;

@Repository
@AllArgsConstructor
public class StockRepositoryImpl implements StockRepository {
    private final StockJpaRepository stockJpaRepository;
    private final StockMapper stockMapper;
    private final ProductServicesImpl productServices;

    @Override
    public List<Stock> getAllStock() {
        List<Stock> stocks = stockMapper.entitiesToStocks(stockJpaRepository.findAll());
        return getStockResponse(stocks);
    }

    @Override
    public Stock getStockById(Long id) {
        StockEntity foundStockEntity = stockJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encuentra un Stock con el id: " + id));
        Stock stock = stockMapper.stockEntityToStock(foundStockEntity);
        Optional<Product> productOptional = getProductForStock(stock);
        productOptional.ifPresent(stock::setProduct);
        return stock;
    }

    @Override
    public Stock saveStock(Stock stock) {
        if (stock == null) {
            throw new IllegalArgumentException("El objeto stock no puede ser nulo");
        }
        StockEntity stockEntity = stockMapper.stockToStockEntity(stock);
        return stockMapper.stockEntityToStock(stockJpaRepository.save(stockEntity));
    }

    @Override
    public List<Stock> findStocksWithAmountAndSellPrice() {
        List<Stock> stocks = stockMapper.entitiesToStocks(stockJpaRepository.findByAmountGreaterThanAndSellPriceIsNotNull(0));
        return getStockResponse(stocks);
    }

    private List<Stock> getStockResponse(List<Stock> stocks) {
        List<Stock> stockResponse = new ArrayList<>();
        for (Stock stock : stocks) {
            Optional<Product> productOptional = getProductForStock(stock);
            productOptional.ifPresent(stock::setProduct);
            stockResponse.add(stock);
        }
        return stockResponse;
    }

    private Optional<Product> getProductForStock(Stock stock) {
        if (stock.getProduct() == null) {
            return Optional.empty();
        }
        return Optional.of(productServices.getProductById(stock.getProduct().getId()));
    }
}
