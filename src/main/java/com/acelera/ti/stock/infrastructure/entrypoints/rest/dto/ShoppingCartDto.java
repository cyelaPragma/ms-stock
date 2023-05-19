package com.acelera.ti.stock.infrastructure.entrypoints.rest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@Getter
@Setter
public class ShoppingCartDto {
    private Long userId;
    private Set<Long> productsId;
    private int amount;
}
