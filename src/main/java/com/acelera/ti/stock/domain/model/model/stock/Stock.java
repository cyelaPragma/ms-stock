package com.acelera.ti.stock.domain.model.model.stock;

import com.acelera.ti.stock.domain.model.model.product.Product;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Stock {
    private Long id;
    private Product product;
    private Double sellPrice;
    private int amount;
}
