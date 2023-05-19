package com.acelera.ti.stock.domain.model.model.product;

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
public class Brand {
    private Long id;
    private String name;
    private String description;
}
