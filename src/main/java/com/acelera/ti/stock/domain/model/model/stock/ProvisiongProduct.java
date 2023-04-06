package com.acelera.ti.stock.domain.model.model.stock;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class ProvisiongProduct {
    private Long id;
    private Stock stock;
    private int amount;
    private Double providerPrice;
}
