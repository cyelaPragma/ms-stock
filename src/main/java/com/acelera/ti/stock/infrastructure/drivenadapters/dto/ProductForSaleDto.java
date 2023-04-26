package com.acelera.ti.stock.infrastructure.drivenadapters.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@Builder
public class ProductForSaleDto implements Serializable {
    private Long id;
    private String name;
    private Double sellPrice;
    private String description;
    private int amount;
}
