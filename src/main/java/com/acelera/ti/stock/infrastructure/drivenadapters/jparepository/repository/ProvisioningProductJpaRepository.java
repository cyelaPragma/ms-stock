package com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.repository;

import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.entity.ProvisioningProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvisioningProductJpaRepository extends JpaRepository<ProvisioningProductEntity, Long> {
}
