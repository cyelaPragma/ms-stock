package com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.repository;

import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.entity.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartJpaRepository extends JpaRepository<ShoppingCartEntity, Long> {
    Optional<ShoppingCartEntity> findByIdUser(Long idUser);
}

