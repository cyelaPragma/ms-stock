package com.acelera.ti.stock.domain.model.model.stock;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ProvisiongProduct {
    private Long id;
    private Stock stock;
    private int amount;
    private Double providerPrice;
}
