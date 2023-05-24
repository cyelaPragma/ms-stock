package com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.repository;

import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.entity.ShoppingCartProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartProductJpaRepository extends JpaRepository<ShoppingCartProductEntity, Long> {
    Optional<ShoppingCartProductEntity> findByIdUser(Long idUser);
}