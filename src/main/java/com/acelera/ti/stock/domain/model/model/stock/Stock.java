package com.acelera.ti.stock.domain.model.model.stock;

import com.acelera.ti.stock.domain.model.model.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Stock {
    private Long id;
    private Product product;
    private Double sellprice;
    private int amount;
}
