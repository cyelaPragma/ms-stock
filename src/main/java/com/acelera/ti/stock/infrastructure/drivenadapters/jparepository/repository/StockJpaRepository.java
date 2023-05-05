package com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.repository;

import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StockJpaRepository extends JpaRepository<StockEntity, Long> {
    List<StockEntity> findByAmountGreaterThanAndSellPriceIsNotNull(int amount);
}
