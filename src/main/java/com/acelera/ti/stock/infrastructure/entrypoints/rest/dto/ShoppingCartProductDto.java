package com.acelera.ti.stock.infrastructure.entrypoints.rest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@Builder
public class ShoppingCartProductDto implements Serializable {
    private String name;
    private String description;
    private Double sellPrice;
    private int amount;
    private int amountStock;
}
