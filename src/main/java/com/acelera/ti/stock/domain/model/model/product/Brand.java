package com.acelera.ti.stock.domain.model.model.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Brand {
    private Long id;
    private String name;
    private String description;
}
