package com.acelera.ti.stock.mock.stock;

import com.acelera.ti.stock.domain.model.model.stock.Provisioning;

import java.time.LocalDate;

public class ProvisioningMocks {
    public static Provisioning getProvisioning(Long id) {
        return Provisioning.builder()
                .id(id)
                .products(ProvisiongProductMocks.getProvisiongProducts(5))
                .providerId(1L)
                .sourcingDate(LocalDate.of(2023, 01, 01))
                .build();
    }
}
