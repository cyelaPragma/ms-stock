package com.acelera.ti.stock.domain.model.model.stock;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@EqualsAndHashCode
public class Provisioning {
    private Long id;
    private Long providerId;
    private LocalDate sourcingDate;
    private List<ProvisiongProduct> products;
}
