package com.acelera.ti.stock.domain.model.model.cart;

import com.acelera.ti.stock.domain.model.model.stock.Stock;
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
public class ShoppingCartProduct {
    private Long id;
    private Stock stock;
    private int amount;
}
