package com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.repository;

import com.acelera.ti.stock.infrastructure.drivenadapters.jparepository.entity.ProvisioningEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvisioningJpaRepository extends JpaRepository<ProvisioningEntity, Long> {
}
