package com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.repository;

import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.entity.ShoppingCartProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartProductJpaRepository extends JpaRepository<ShoppingCartProductEntity, Long> {
}
